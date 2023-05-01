package io.tiago;

import org.jboss.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class HiderImpl implements Hider {

    private static final Logger LOG = Logger.getLogger(HiderImpl.class);
    private static final String CANIS_FILE = "data.txt";

    @Override
    public void help() {
        Arrays.asList(Menu.values()).stream().forEach(m -> System.out.println(m.value()));
    }

    @Override
    public void init() throws Exception {
        Crypto.init();
    }

    @Override
    public String enc(String input, Argument argument) throws Exception {
        String message = Crypto.encrypt(input);     
        System.out.println("Encrypted message:");
        System.out.println(message);   
        if (argument != null) {
            Files.write(Paths.get(CANIS_FILE), message.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        }
        return message;
    }

    @Override
    public String dec(String input, Argument argument) throws Exception {        
        String message = Crypto.decrypt(input); 
        System.out.println("Decrypted message:");
        System.out.println(message);       
        if (argument != null) {
            for (String m : Files.readAllLines(Paths.get(CANIS_FILE))) {
                System.out.println(Crypto.decrypt(m));
            }
        }
        return message;
    }
}
