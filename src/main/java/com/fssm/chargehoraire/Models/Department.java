package com.fssm.chargehoraire.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department",cascade = {} ,orphanRemoval = false)
    @JsonIgnoreProperties({"department","modules"})
    private List<Field> fields = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = {})
    @JoinTable(name = "AdminTask",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();
}