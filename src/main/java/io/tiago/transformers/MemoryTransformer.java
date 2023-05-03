package io.tiago.transformers;

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
        return message;
    }
}
