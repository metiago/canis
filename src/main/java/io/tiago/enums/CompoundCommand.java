package io.tiago.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CompoundCommand {

    ENC("enc"),
    DEC("dec");

    private String value;

    CompoundCommand(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static List<String> getValues() {
        return Stream.of(CompoundCommand.values()).map(m->m.value()).collect(Collectors.toList());
    }
}
