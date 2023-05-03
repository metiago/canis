package io.tiago;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;
import io.tiago.enums.ConsoleMessage;

@QuarkusMainTest
public class InitCommandTest {

    @Test
    @Launch(value = {}, exitCode = 1)
    public void When_Invalid_Command_Then_Fail_Code_1() {
    }

    @Test
    @Launch(value = { "init" }, exitCode = 0)
    public void When_Valid_Command_Then_OK_Code_0() {
    }

    @Test
    @Launch(value = { "init" }, exitCode = 0)
    public void When_Init_Command_Then_Valid(LaunchResult result) {
        Assertions.assertTrue(ConsoleMessage.INIT_START.value().equals("Creating keys..."));
    }
}