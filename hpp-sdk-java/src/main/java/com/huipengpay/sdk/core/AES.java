package com.huipengpay.sdk.core;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加解密工具
 */
public class AES {

    public static final String AES_TYPE = "AES/ECB/PKCS5Padding";

    /**
     * 生成AES秘钥
     */
    public static String generateKey() {
        KeyGenerator keygen = null;
        try {
            keygen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        SecureRandom random = new SecureRandom();
        keygen.init(128, random);
        return Base64.encodeBase64String(keygen.generateKey().getEncoded());
    }

    public static Key stringToKey(String keyStr) {
        byte[] bytes = Base64.decodeBase64(keyStr);
        return new SecretKeySpec(bytes, 0, bytes.length, "AES");
    }


    /**
     * 加密
     */
    public static String encrypt(String key, String plainTextData) {
        try {
            Cipher cipher = init(key, Cipher.ENCRYPT_MODE);
            return Base64.encodeBase64String(cipher.doFinal(plainTextData.getBytes("UTF-8")));
        } catch (IOException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     */
    public static String decrypt(String key, String cipherData) {
        try {
            Cipher cipher = init(key, Cipher.DECRYPT_MODE);
            return new String(cipher.doFinal(Base64.decodeBase64(cipherData)), "UTF-8");
        } catch (IOException | ShortBufferException | BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Cipher init(String keyStr, int mode) throws IOException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(AES_TYPE);
        Key key = stringToKey(keyStr);
        cipher.init(mode, key);
        return cipher;
    }

}
