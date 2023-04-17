package com.fssm.chargehoraire.Requests;
import lombok.Data;

@Data
public class AdmintaskRequest {
    private long teacher_id;
    private long department_id;
    private String year;
    private float hours;
}
