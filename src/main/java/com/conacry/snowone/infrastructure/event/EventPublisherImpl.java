package com.conacry.snowone.infrastructure.event;

import com.conacry.snowone.application.port.EventPublisher;
import com.conacry.snowone.shared.mark.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherImpl implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public <T extends DomainEvent> void publish(T event) {
        applicationEventPublisher.publishEvent(event);
    }
}
