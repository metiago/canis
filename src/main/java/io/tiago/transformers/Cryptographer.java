package io.tiago.transformers;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import io.tiago.pojos.Vault;


public class Cryptographer {

    public Vault init() throws Exception {
        byte[] initVector = getRandomNonce();
        byte[] key = getRandomNonce();
        String encodedIV = Base64.getEncoder().encodeToString(initVector);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encodedSalt = Base64.getEncoder().encodeToString(getRandomSalt().getBytes());
        
        Files.write(Paths.get(Constants.VECTOR_FILE), encodedIV.getBytes());
        Files.write(Paths.get(Constants.SECRET_KEY_FILE), encodedKey.getBytes());
        Files.write(Paths.get(Constants.SALT_FILE), encodedSalt.getBytes());        
        
        return new Vault(encodedIV, encodedKey, encodedSalt);
    }

    public String encrypt(String value) throws Exception {
        byte[] fileBytes = Files.readAllBytes(Paths.get(Constants.VECTOR_FILE));
        IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(fileBytes));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, getScretKey(), iv);
        byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String value) throws Exception {     
        byte[] fileBytes = Files.readAllBytes(Paths.get(Constants.VECTOR_FILE)); 
        IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(fileBytes));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, getScretKey(), iv);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(original);
    }

    private byte[] getRandomNonce() {
        byte[] nonce = new byte[16];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    private SecretKeySpec getScretKey() throws Exception {
        byte[] secretBytes = Files.readAllBytes(Paths.get(Constants.SECRET_KEY_FILE));
        byte[] saltBytes = Files.readAllBytes(Paths.get(Constants.SALT_FILE));
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(new String(secretBytes).toCharArray(), saltBytes, 65536, 256);
        SecretKey secretKey = factory.generateSecret(spec);
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }

    private String getRandomSalt() {
        var chars = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
        
        var specialChars = List.of("!", "#", "$", "%", "&", "*", "+", "-", ".",":", "?", "@", "~");

        StringBuilder builder = new StringBuilder();
        final int saltSize = 16;
        for(int i = 0; i < saltSize; i++) {
            int randomIndx = new Random().nextInt(0, chars.size() - 1);
            int randomSpecialIdx = new Random().nextInt(0, specialChars.size() - 1);
            if(randomIndx * specialChars.size() % 2 == 0) {
                builder.append(chars.get(randomIndx));
            } else {
                builder.append(specialChars.get(randomSpecialIdx));
            }
        }

        return builder.toString();
    }
}
