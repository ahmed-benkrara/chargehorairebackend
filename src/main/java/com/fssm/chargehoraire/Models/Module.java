package com.fssm.chargehoraire.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {})
    @JsonIgnoreProperties({"modules","department"})
    private Field field = new Field();
    @ManyToMany(fetch = FetchType.LAZY,cascade = {})
    @JoinTable(name = "Teaches",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();
}