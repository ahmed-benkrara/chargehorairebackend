package com.fssm.chargehoraire.Requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
