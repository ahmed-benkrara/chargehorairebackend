package com.fssm.chargehoraire.Requests;

import lombok.Data;

@Data
public class FieldUpdateRequest {
    private Long id;
    private String name;
    private Long department_id;
}
