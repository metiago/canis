package io.tiago;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.QuarkusMainTest;
import io.tiago.encoders.Encoder;
import io.tiago.encoders.MemoryEnconder;
import io.tiago.pojos.Payload;

@QuarkusMainTest
public class EncCommandTest {

    @BeforeAll
    public static void setUp() throws Exception {
        Encoder encoder = new MemoryEnconder();
        encoder.init();
    }

    @Test
    @Launch(value = {"enc"}, exitCode = 1)
    public void When_Invalid_Command_Then_Fail_Code_1() {
    }

    @Test
    public void When_Enc_Command_Then_OK() throws Exception {
        Encoder encoder = new MemoryEnconder();
        String plain = "Oh boy! What am I doing ?";
        String encrypted = encoder.enc(new Payload(plain));
        Assertions.assertFalse(plain.equals(encrypted));
        String decripted = encoder.dec(new Payload(encrypted));
        Assertions.assertTrue(plain.equals(decripted));
    }
}