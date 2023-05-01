package io.tiago;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;

public final class Crypto {

    private static final Logger LOG = Logger.getLogger(Crypto.class);
    private static final String VECTOR_FILE = "canis_vector.txt";
    private static final String SECRET_KEY_FILE = "canis_secret_key.txt";

    public static void init() throws Exception {
        LOG.info(Messages.INIT_START);
        byte[] key = getRandomNonce();
        byte[] initVector = getRandomNonce();
        // generate key and vector encoding its value with base64
        String a = Base64.getEncoder().encodeToString(key);
        String b = Base64.getEncoder().encodeToString(initVector);
        Files.write(Paths.get(SECRET_KEY_FILE), a.getBytes());
        Files.write(Paths.get(VECTOR_FILE), b.getBytes());
        LOG.info(Messages.INIT_DONE);
    }

    public static String encrypt(String value) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(readVectorFile());
        SecretKeySpec skeySpec = new SecretKeySpec(readKeyFile(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String value) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(readVectorFile());
        SecretKeySpec skeySpec = new SecretKeySpec(readKeyFile(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(original);
    }

    private static byte[] getRandomNonce() {
        byte[] nonce = new byte[16];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    private static byte[] readVectorFile() throws Exception {
        try (BufferedReader vectorReader = new BufferedReader(new FileReader(VECTOR_FILE))) {
            return Base64.getDecoder().decode(vectorReader.readLine());
        } catch (IOException e) {
            throw new Exception("Vector file not found. Consider running init command.");
        }
    }

    private static byte[] readKeyFile() throws Exception {
        try (BufferedReader keyReader = new BufferedReader(new FileReader(SECRET_KEY_FILE))) {
            return Base64.getDecoder().decode(keyReader.readLine());
        } catch (IOException e) {
            throw new Exception("Key file not found. Consider running init command.");
        }
    }

}
