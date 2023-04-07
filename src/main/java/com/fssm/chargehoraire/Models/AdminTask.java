package com.fssm.chargehoraire.Models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class AdminTask {
    @EmbeddedId
    private AdminTaskKey key;
    private float hours;
}
