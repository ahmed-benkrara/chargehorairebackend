package com.fssm.chargehoraire.Security;

import java.util.Random;

public class EmailToken {
    public static String generateToken(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String token = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            token += chars.charAt(index);
        }
        return token;
    }
}
