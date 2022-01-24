package com.conacry.post.infrastructure.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "elf_info")
public class ElfDbModel {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "address", nullable = false)
    private String address;
}
