package com.fssm.chargehoraire.Requests;

import lombok.Data;

@Data
public class EmailTokenRequest {
    private String email;
    private String token;
}
