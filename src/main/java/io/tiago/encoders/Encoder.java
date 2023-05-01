package io.tiago.encoders;

import io.tiago.pojos.Payload;

public interface Encoder {
    void help();
    void init() throws Exception;
    String enc(Payload payload) throws Exception;
    String dec(Payload payload) throws Exception;
}
