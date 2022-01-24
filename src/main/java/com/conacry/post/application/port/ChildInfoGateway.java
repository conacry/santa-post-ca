package com.conacry.post.application.port;

import com.conacry.post.domain.value.Child;

import java.util.Optional;

public interface ChildInfoGateway {

    Optional<Child> findByName(String childName);
}
