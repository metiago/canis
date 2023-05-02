package io.tiago.enums;

public enum ConsoleMessage {
    
    INIT_START("Creating IV and Secret Keys"),
    INIT_DONE("Done");

    private String value;

    ConsoleMessage(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
