package com.fssm.chargehoraire.Security;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TokenSigner {
    private final String key;

    public TokenSigner(String key) {
        this.key = key;
    }

    public String signToken(String token) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        mac.init(secretKey);
        byte[] signature = mac.doFinal(token.getBytes());
        return token + "." + Base64.getUrlEncoder().withoutPadding().encodeToString(signature);
    }

    public boolean verifyToken(String signedToken) throws NoSuchAlgorithmException, InvalidKeyException {
        String[] parts = signedToken.split("\\.");
        String token = parts[0] + "." + parts[1] + "." + parts[2];
        byte[] signature = Base64.getUrlDecoder().decode(parts[3]);
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        mac.init(secretKey);
        byte[] computedSignature = mac.doFinal(token.getBytes());
        return MessageDigest.isEqual(signature, computedSignature);
    }
}
