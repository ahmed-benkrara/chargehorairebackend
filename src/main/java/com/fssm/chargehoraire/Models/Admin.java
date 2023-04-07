package com.fssm.chargehoraire.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue(value = "teacher")
public class Admin extends User{
    //0 light, 1 dark
    private int theme;
}
