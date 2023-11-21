package com.connectis.connectis.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "province")
@Setter
@Getter
public class ProvinceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;}
