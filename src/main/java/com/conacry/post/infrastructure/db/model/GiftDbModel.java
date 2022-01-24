package com.conacry.post.infrastructure.db.model;

import com.conacry.post.infrastructure.db.model.ChildDbModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "gift")
public class GiftDbModel {

    @Id
    private Integer id;

    @Column(name = "size")
    private String size;

    @OneToOne
    @JoinColumn(name = "child_name", referencedColumnName = "name", nullable = false)
    private ChildDbModel childDbModel;
}
