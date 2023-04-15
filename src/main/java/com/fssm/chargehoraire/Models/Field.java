package com.fssm.chargehoraire.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIgnoreProperties("fields")
    private Department department = new Department();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "field")
    private List<Module> modules = new ArrayList<>();
}
