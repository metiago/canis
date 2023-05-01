package io.tiago;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.QuarkusMainTest;

@QuarkusMainTest
public class DecCommandTest {

    @Test
    @Launch(value = {"dec"}, exitCode = 1)
    public void When_Invalid_Command_Then_Fail_Code_1() {
    }

    @Test
    @Launch(value = { "help" }, exitCode = 0)
    public void When_Valid_Command_Then_OK_Code_0() {
    }

    @Test
    public void When_Dec_Command_Then_OK() throws Exception {
        Hider hider = new HiderImpl();
        String plain = "Copy and paste are never a bad thing for tests.";
        String encrypted = hider.enc(plain, null);
        Assertions.assertFalse(plain.equals(encrypted));
        String decripted = hider.dec(encrypted, null);
        Assertions.assertTrue(plain.equals(decripted));
    }

    @Test
    public void When_Dec_Wrong_Param_Then_Fail() throws Exception {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            Hider hider = new HiderImpl();
            hider.dec("encrypted", null);
        });
        Assertions.assertEquals("Last unit does not have enough valid bits", thrown.getMessage());        
    }
}