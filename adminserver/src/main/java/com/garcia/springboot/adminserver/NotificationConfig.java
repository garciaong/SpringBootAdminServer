package com.garcia.springboot.adminserver;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.CompositeNotifier;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.notify.RemindingNotifier;
import de.codecentric.boot.admin.server.notify.filter.FilteringNotifier;

@Configuration
public class NotificationConfig {

	private final InstanceRepository repository;
	private final ObjectProvider<List<Notifier>> otherNotifiers;

	public NotificationConfig(InstanceRepository repository, ObjectProvider<List<Notifier>> otherNotifiers) {
        this.repository = repository;
        this.otherNotifiers = otherNotifiers;
    }

	@Bean
	public FilteringNotifier filteringNotifier() {
		CompositeNotifier delegate = new CompositeNotifier(this.otherNotifiers.getIfAvailable(Collections::emptyList));
		return new FilteringNotifier(delegate, this.repository);
	}

	@Primary
	@Bean(initMethod = "start", destroyMethod = "stop")
	public RemindingNotifier remindingNotifier() {
		RemindingNotifier notifier = new RemindingNotifier(filteringNotifier(), this.repository);
		//The reminders will be sent every 10 minutes
//		notifier.setReminderPeriod(Duration.ofMinutes(10));
		notifier.setReminderPeriod(Duration.ofSeconds(5));
		//Schedules sending of due reminders every 10 seconds
		notifier.setCheckReminderInverval(Duration.ofSeconds(5));
		return notifier;
	}

}
