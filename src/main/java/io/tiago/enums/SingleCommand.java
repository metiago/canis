package io.tiago.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SingleCommand {
    
    HELP("help"),
    INIT("init");

    private String value;

    SingleCommand(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static List<String> getValues() {
        return Stream.of(SingleCommand.values()).map(m->m.value()).collect(Collectors.toList());
    }
}
