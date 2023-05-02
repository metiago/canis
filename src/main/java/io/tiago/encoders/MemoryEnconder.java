package io.tiago.encoders;

import java.util.Arrays;

import io.tiago.enums.Menu;
import io.tiago.pojos.Payload;
import io.tiago.utils.CryptoUtil;

public class MemoryEnconder implements Encoder {

    @Override
    public void help() {
        Arrays.asList(Menu.values()).stream().forEach(m -> System.out.println(m.value()));
    }

    @Override
    public void init() throws Exception {
        CryptoUtil.init();
    }

    @Override
    public String enc(Payload payload) throws Exception {
        String message = CryptoUtil.encrypt(payload.getContent());     
        System.out.println("Encrypted message:");
        System.out.println(message);   
        return message;
    }

    @Override
    public String dec(Payload payload) throws Exception {        
        String message = CryptoUtil.decrypt(payload.getContent()); 
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
