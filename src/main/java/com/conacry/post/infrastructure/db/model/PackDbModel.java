package com.conacry.post.infrastructure.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
