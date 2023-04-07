package com.fssm.chargehoraire.Models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class AdminTaskKey {
    private long teacher_id;
    private long department_id;
    @ManyToOne
    private Year year;
}
