package com.fssm.chargehoraire.Requests;

import lombok.Data;

@Data
public class ForgotRequest {
    private String email;
    private String route;
}
