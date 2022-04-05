package com.conacry.snowone.application.port;

import com.conacry.snowone.domain.value.Child;

import java.util.Optional;

public interface ChildInfoGateway {

    Optional<Child> findByName(String childName);
}
