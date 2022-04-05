package com.conacry.snowone.infrastructure.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pack")
public class PackDbModel {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "size")
    private String size;

    @Column(name = "type")
    private String type;
}
