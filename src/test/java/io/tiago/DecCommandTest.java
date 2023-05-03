package io.tiago;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.QuarkusMainTest;
import io.tiago.encoders.Transformer;
import io.tiago.encoders.MemoryTransformer;
import io.tiago.pojos.Payload;

@QuarkusMainTest
public class DecCommandTest {

    @BeforeAll
    public static void setUp() throws Exception {
        Transformer transformer = new MemoryTransformer();
        transformer.init();
    }

    @Test
    @Launch(value = {"dec"}, exitCode = 1)
    public void When_Invalid_Command_Then_Fail_Code_1() {
    }

    @Test
    public void When_Dec_Command_Then_OK() throws Exception {
        Transformer transformer = new MemoryTransformer();
        String plain = "Copy and paste are never a bad thing for tests.";
        String encrypted = transformer.encrypt(new Payload(plain));
        Assertions.assertFalse(plain.equals(encrypted));
        String decripted = transformer.decrypt(new Payload(encrypted));
        Assertions.assertTrue(plain.equals(decripted));
    }

    @Test
    public void When_Dec_Wrong_Param_Then_Fail() throws Exception {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            Transformer transformer = new MemoryTransformer();
            transformer.decrypt(new Payload("encrypted"));
        });
        Assertions.assertEquals("Last unit does not have enough valid bits", thrown.getMessage());        
    }
}