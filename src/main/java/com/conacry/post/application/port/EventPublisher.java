package com.conacry.post.application.port;

import com.conacry.post.domain.event.DomainEvent;

public interface EventPublisher {

    <T extends DomainEvent> void publish(T event);
}
