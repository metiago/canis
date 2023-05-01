package io.tiago.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class FileUtil {

    public static byte[] readVectorFile() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.VECTOR_FILE))) {
            return Base64.getDecoder().decode(reader.readLine());
        } catch (IOException e) {
            throw new Exception("Vector file not found. Consider running init command.");
        }
    }

    public static byte[] readKeyFile() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.SECRET_KEY_FILE))) {
            return Base64.getDecoder().decode(reader.readLine());
        } catch (IOException e) {
            throw new Exception("Key file not found. Consider running init command.");
        }
    }

    public static void write(String file, String content) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
