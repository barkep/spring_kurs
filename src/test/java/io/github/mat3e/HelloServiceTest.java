package io.github.mat3e;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {

    private HelloService SUT = new HelloService();

    @Test
    public void test_prepareGreeting_null_returnsGreetingWithFallback() {
        String name = null;
        var result = SUT.prepareGreeting(name);
        assertEquals("Hello world !", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        String name = "test";
        var result = SUT.prepareGreeting(name);
        assertEquals("Hello " + name + " !", result);
    }
}
