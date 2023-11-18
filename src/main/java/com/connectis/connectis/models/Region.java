package com.connectis.connectis.models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id",nullable = false)
    private Short id;
    @Column(name = "name",nullable = false)
    private String name;

}
