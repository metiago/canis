package io.tiago;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainLauncher;
import io.quarkus.test.junit.main.QuarkusMainTest;
import io.tiago.pojos.Payload;
import io.tiago.transformers.MemoryTransformer;
import io.tiago.transformers.Transformer;

@QuarkusMainTest
public class EncCommandTest {

    @BeforeAll
    public static void setUp() throws Exception {
        Transformer transformer = new MemoryTransformer();
        transformer.init();
    }

    @Test
    @Launch(value = {"enc"}, exitCode = 1)
    public void When_Valid_Command_Then_OK() {
    }

    // @Test
    // @Launch
    // public void When_Special_Char_In_Text_Then_Fail(QuarkusMainLauncher launcher) {
    //     String command = "enc";
    //     String text = "I dont";
    //     LaunchResult result = launcher.launch(command, text);
    //     Assertions.assertEquals(1, result.exitCode());
    // }

    @Test
    public void When_Enc_Command_Then_OK() throws Exception {
        Transformer transformer = new MemoryTransformer();
        String plain = "Oh boy! What am I doing ?";
        String encrypted = transformer.encrypt(new Payload(plain));
        Assertions.assertFalse(plain.equals(encrypted));
        String decripted = transformer.decrypt(new Payload(encrypted));
        Assertions.assertTrue(plain.equals(decripted));
    }

    @Test
    public void When_Enc_Command_With_Special_Char_Then_OK() throws Exception {
        Transformer transformer = new MemoryTransformer();
        String plain = "I don't know why I can't grab it.";
        String encrypted = transformer.encrypt(new Payload(plain));
        Assertions.assertFalse(plain.equals(encrypted));
        String decripted = transformer.decrypt(new Payload(encrypted));
        Assertions.assertTrue(plain.equals(decripted));
    }
}