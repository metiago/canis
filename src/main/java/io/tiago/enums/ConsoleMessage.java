package io.tiago.enums;

public enum ConsoleMessage {
    
    INIT_START("Creating keys...");

    private String value;

    ConsoleMessage(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
