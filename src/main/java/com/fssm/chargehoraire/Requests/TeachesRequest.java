package com.fssm.chargehoraire.Requests;

import lombok.Data;

@Data
public class TeachesRequest {
    private long teacher_id;
    private long module_id;
    private String year;
    private String semester;
    private String session;
    private String typeh;
    private float hours;
}
