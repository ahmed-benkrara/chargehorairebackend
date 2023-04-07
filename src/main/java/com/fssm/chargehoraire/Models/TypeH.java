package com.fssm.chargehoraire.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TypeH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
}
