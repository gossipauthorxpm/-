package com.example.bankinformationsystem.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Hex;

public class Encoder {

    private static final String SECRET_KEY = "j8IlKJS2OP09jj16";
    private static final String INIT_VECTOR = "cD8Koi0p76Jyr21h";

    public static String encrypt(String text_for_encrypt){
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            return new String(Hex.encodeHex(cipher.doFinal(text_for_encrypt.getBytes(StandardCharsets.UTF_8))));

        } catch (Exception e) {
            System.out.println(e + "Error");
            return null;
        }
    }
    public static String decrypt(String text_for_decrypt){
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            return new String(cipher.doFinal(Hex.decodeHex(text_for_decrypt.toCharArray())));

        }catch (Exception e){
            System.out.println(e + "Error");
            return null;
        }
    }
}
