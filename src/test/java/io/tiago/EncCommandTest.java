package io.tiago;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.QuarkusMainTest;

@QuarkusMainTest
public class EncCommandTest {

    @Test
    @Launch(value = {"enc"}, exitCode = 1)
    public void When_Invalid_Command_Then_Fail_Code_1() {
    }

    @Test
    @Launch(value = { "help" }, exitCode = 0)
    public void When_Valid_Command_Then_OK_Code_0() {
    }

    @Test
    public void When_Enc_Command_Then_OK() throws Exception {
        Hider hider = new HiderImpl();
        String plain = "Oh boy! What am I doing ?";
        String encrypted = hider.enc(plain, null);
        Assertions.assertFalse(plain.equals(encrypted));
        String decripted = hider.dec(encrypted, null);
        Assertions.assertTrue(plain.equals(decripted));
    }
}