package com.fssm.chargehoraire.Models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Teaches {
    @EmbeddedId
    private TeachesKey key;
    @OneToOne
    private Semester semester;
    @OneToOne
    private Session session;
    @OneToOne
    private TypeH type;
    private float hours;
}
