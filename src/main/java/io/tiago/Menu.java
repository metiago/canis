package io.tiago;

public enum Menu {
    
    COMMANDS("Commands:"),
    INIT(String.format("%s%-9s%s%n", "init", " ", "Generate private keys")),
    ENC(String.format("%s%-10s%s%n", "enc", " ", "Encrypt input value.")),
    DEC(String.format("%s%-10s%s%n", "dec", " ", "Decrypt input value.")),
    ARGUMENTS("Arguments:"),
    ARG_F(String.format("%s%-10s%s%n", "--f", " ", "Optional - save message to file."));

    private String value;

    Menu(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
