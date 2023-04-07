package com.fssm.chargehoraire.Models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class TeachesKey {
    private long teacher_id;
    private long module_id;
    @ManyToOne
    private Year year;
}
