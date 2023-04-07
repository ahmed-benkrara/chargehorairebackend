package com.fssm.chargehoraire.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue(value = "teacher")
public class Teacher extends User{
    @Column(unique = true)
    private String cin;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "teachers")
    private Set<Department> departments = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "teachers")
    private Set<Module> modules = new HashSet<>();
}
