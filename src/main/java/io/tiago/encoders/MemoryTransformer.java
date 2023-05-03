package io.tiago.encoders;

import java.util.Arrays;

import io.tiago.enums.ConsoleMessage;
import io.tiago.enums.Menu;
import io.tiago.pojos.Payload;
import io.tiago.pojos.Vault;

public class MemoryTransformer implements Transformer {

    private final Cryptographer cryptographer;

    public MemoryTransformer() {
        this.cryptographer = new Cryptographer();
    }

    @Override
    public void help() {
        Arrays.asList(Menu.values()).stream().forEach(m -> System.out.println(m.value()));
    }

    @Override
    public void init() throws Exception {
        System.out.println(ConsoleMessage.INIT_START.value());
        Vault vault = cryptographer.init();
        System.out.println(vault);
    }

    @Override
    public String encrypt(Payload payload) throws Exception {
        String message = cryptographer.encrypt(payload.getContent());     
        System.out.println("Encrypted message:");
        System.out.println(message);   
        return message;
    }

    @Override
    public String decrypt(Payload payload) throws Exception {        
        String message = cryptographer.decrypt(payload.getContent()); 
        System.out.println("Decrypted message:");
        System.out.println(message);
        
        
        // if (argument != null) {
        //     for (String m : Files.readAllLines(Paths.get(CANIS_FILE))) {
        //         System.out.println(CryptoUtil.decrypt(m));
        //     }
        // }
        // if (argument != null) {
        //     Files.write(Paths.get(CANIS_FILE), message.getBytes(),
        //             StandardOpenOption.CREATE,
        //             StandardOpenOption.TRUNCATE_EXISTING);
        // }
        return message;
    }
}
