package io.tiago.enums;

public enum Menu {
    
    COMMANDS("Commands:"),
    INIT(String.format("%s%-9s%s", "init", " ", "Generate private keys.")),
    ENC(String.format("%s%-10s%s", "enc", " ", "Encrypt input value.")),
    DEC(String.format("%s%-10s%s%n", "dec", " ", "Decrypt input value.")),
    ARGUMENTS("General Options:"),
    ARG_F(String.format("%s%-10s%s%n", "--p", " ", "Save text to a .txt file."));

    private String value;

    Menu(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
