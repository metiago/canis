package io.tiago.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Option {

    PERSIST("--p");

    private String value;

    Option(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static List<String> getValues() {
        return Stream.of(Option.values()).map(m -> m.value()).collect(Collectors.toList());
    }
}
