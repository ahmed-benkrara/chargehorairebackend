package com.fssm.chargehoraire.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department = new Department();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "field")
    private List<Module> modules = new ArrayList<>();
}
