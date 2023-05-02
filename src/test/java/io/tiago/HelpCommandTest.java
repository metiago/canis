package io.tiago;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;
import io.tiago.enums.Menu;

@QuarkusMainTest
public class HelpCommandTest {

    @Test
    @Launch(value = {}, exitCode = 1)
    public void When_Invalid_Command_Then_Fail_Code_1() {
    }

    @Test
    @Launch(value = { "help" }, exitCode = 0)
    public void When_Help_Command_Then_Valid_Menu_Options(LaunchResult result) {
        
        String values[] = new String[] {
            "Commands:",
            String.format("%s%-9s%s%n", "init", " ", "Generate private keys."),
            String.format("%s%-10s%s%n", "enc", " ", "Encrypt input value."),
            String.format("%s%-10s%s%n", "dec", " ", "Decrypt input value."),
            "General Options:",
            String.format("%s%-10s%s%n", "--p", " ", "Save text to a .txt file.")
        };
        
        List<String> menus = Arrays.asList(Menu.values()).stream().map(Menu::value).collect(Collectors.toList());

        Assertions.assertTrue(menus.equals(Arrays.asList(values)));
    }
}