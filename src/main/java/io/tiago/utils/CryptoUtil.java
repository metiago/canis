package io.tiago.utils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.jboss.logging.Logger;

import io.tiago.enums.ConsoleMessage;

public final class CryptoUtil {

    private static final Logger LOG = Logger.getLogger(CryptoUtil.class);
    

    public static void init() throws Exception {
        LOG.info(ConsoleMessage.INIT_START);
        byte[] key = getRandomNonce();
        byte[] initVector = getRandomNonce();
        // generate key and vector encoding its value with base64
        String a = Base64.getEncoder().encodeToString(key);
        String b = Base64.getEncoder().encodeToString(initVector);
        FileUtil.write(Constants.SECRET_KEY_FILE, a);
        FileUtil.write(Constants.VECTOR_FILE, b);
        LOG.info(ConsoleMessage.INIT_DONE);
    }

    public static String encrypt(String value) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(FileUtil.readVectorFile());
        SecretKeySpec skeySpec = new SecretKeySpec(FileUtil.readKeyFile(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String value) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(FileUtil.readVectorFile());
        SecretKeySpec skeySpec = new SecretKeySpec(FileUtil.readKeyFile(), "AES");
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
}
