package com.conacry.post.application.port;

import com.conacry.post.domain.value.Address;
import com.conacry.post.domain.value.Elf;

import javax.annotation.Nullable;
import java.util.Optional;

public interface ElfInfoGateway {

    Optional<Elf> findByAddress(@Nullable Address address);

    Optional<Elf> findByName(@Nullable String name);
}
