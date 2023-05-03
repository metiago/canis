package io.tiago.encoders;

import io.tiago.pojos.Payload;

public interface Transformer {
    void help();
    void init() throws Exception;
    String encrypt(Payload payload) throws Exception;
    String decrypt(Payload payload) throws Exception;
}
