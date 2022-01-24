package com.conacry.snowone.infrastructure.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "delivery_order")
public class DeliveryOrderDbModel {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "state", nullable = false)
    private String state;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pack_id", referencedColumnName = "id", nullable = false)
    private PackDbModel pack;

    @OneToOne
    @JoinColumn(name = "gift_id", referencedColumnName = "id", nullable = false)
    private GiftDbModel gift;

    @ManyToOne
    @JoinColumn(name = "elf_name", referencedColumnName = "name")
    private ElfDbModel elf;
}
