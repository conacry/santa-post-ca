package com.conacry.snowone.application.port;

import com.conacry.snowone.shared.mark.DomainEvent;

public interface EventPublisher {

    <T extends DomainEvent> void publish(T event);
}
