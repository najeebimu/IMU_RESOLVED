package com.softorea.schoolsen.lists;

import android.media.MediaCodec;

import java.io.File;
import java.io.FileInputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class CryptoUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    /* This function is used for xml encryption */
    public static byte[] encrypt(String key, File inputFile)
            throws MediaCodec.CryptoException {
        byte[] encrypt = doCrypto(Cipher.ENCRYPT_MODE, key, inputFile);

        return encrypt;
    }

    /* This function is used for xml decryption */
    public static byte[] decrypt(String key, File inputFile)
            throws MediaCodec.CryptoException {
        byte[] decrypt = doCrypto(Cipher.DECRYPT_MODE, key, inputFile);
        return decrypt;
    }

    /* This function is used for xml encryption */
    private static byte[] doCrypto(int cipherMode, String key, File inputFile) throws MediaCodec.CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            inputStream.close();
            return outputBytes;

        } catch (Exception ex) {
            throw new MediaCodec.CryptoException(0, "Error encrypting/decrypting file");
        }
    }

}
