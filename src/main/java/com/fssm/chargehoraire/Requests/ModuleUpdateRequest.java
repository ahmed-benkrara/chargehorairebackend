package com.fssm.chargehoraire.Requests;

import lombok.Data;

@Data
public class ModuleUpdateRequest {
    private Long id;
    private String name;
    private Long field_id;
}
