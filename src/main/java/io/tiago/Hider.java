package io.tiago;

public interface Hider {
    void help();
    void init() throws Exception;
    String enc(String input, Argument argument) throws Exception;
    String dec(String input, Argument argument) throws Exception;
}
