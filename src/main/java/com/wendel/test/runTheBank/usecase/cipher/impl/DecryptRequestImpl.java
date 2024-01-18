package com.wendel.test.runTheBank.usecase.cipher.impl;

import com.wendel.test.runTheBank.usecase.cipher.DecryptRequest;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class DecryptRequestImpl implements DecryptRequest {
    @Override
    public String execute(String request){

        String decryptionKeyString = "someKeyOf16Bytes";

        try {
            byte[] decryptionKeyBytes = decryptionKeyString.getBytes();

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(decryptionKeyBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decryptedMessageBytes = cipher.doFinal(Base64.getDecoder().decode(request));

            return new String(decryptedMessageBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            System.out.println("Error in encrypting data");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
