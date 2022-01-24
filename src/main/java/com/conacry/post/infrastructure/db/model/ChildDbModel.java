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
@Table(name = "child_info")
public class ChildDbModel {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "behavior")
    private String behavior;
}
