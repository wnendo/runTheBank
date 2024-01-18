package com.wendel.test.runTheBank.usecase.cipher.impl;

import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
@Service
public class EncryptRequestImpl implements EncryptRequest {
    @Override
    public String execute(String request) {

        try {
            String encryptionKeyString = "someKeyOf16Bytes";
            byte[] encryptionKeyBytes = encryptionKeyString.getBytes();

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedMessageBytes = cipher.doFinal(request.getBytes());

            return Base64.getEncoder().encodeToString(encryptedMessageBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
