package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private static String WELCOME = "Hello";


    @Test
    public void test_prepareGreeting_null_returnsGreetingWithFallbackName() {
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        var result = SUT.prepareGreeting(null, "-1");
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + " !", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        String name = "test";
        var result = SUT.prepareGreeting(name, "-1");
        assertEquals(WELCOME + " " + name + " !", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        var fallbackIdWelcome = "Hola";
        var mockRepository = new LangRepository() {
            @Override
            Optional<Lang> findByID(Long id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, fallbackIdWelcome, null));
                }
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);
        var result = SUT.prepareGreeting(null, null);
        assertEquals(fallbackIdWelcome + " " + HelloService.FALLBACK_NAME + " !", result);
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findByID(Long id) {
                return Optional.of(new Lang(null, "Hello", null));
            }
        };
    }
}
