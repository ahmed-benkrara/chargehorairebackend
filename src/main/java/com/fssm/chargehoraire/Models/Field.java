package com.fssm.chargehoraire.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToOne(fetch = FetchType.EAGER,cascade = {})
    @JsonIgnoreProperties({"fields","teachers"})
    private Department department = new Department();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "field", cascade = {} ,orphanRemoval = false)
    @JsonIgnoreProperties({"field","teachers"})
    private List<Module> modules = new ArrayList<>();
}