package com.connectis.connectis.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;

}
