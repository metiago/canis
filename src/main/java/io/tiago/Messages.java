package io.tiago;

public enum Messages {
    
    INIT_START("Creating IV and Secret Keys"),
    INIT_DONE("Done");

    private String value;

    Messages(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
