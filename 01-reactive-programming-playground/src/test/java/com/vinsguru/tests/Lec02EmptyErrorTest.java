package com.vinsguru.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {

    Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty(); // null
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    @Test
    public void userTest() {
        StepVerifier.create(getUsername(1))
                    .expectNext("sam")
                    .expectComplete()
                    .verify(); // subscribe
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUsername(2))
                    .expectComplete()
                    .verify(); // subscribe
    }

    @Test
    public void errorTest1() {
        StepVerifier.create(getUsername(3))
                    .expectError()
                    .verify(); // subscribe
    }

    @Test
    public void errorTest2() {
        StepVerifier.create(getUsername(3))
                    .expectError(RuntimeException.class)
                    .verify(); // subscribe
    }

    @Test
    public void errorTest3() {
        StepVerifier.create(getUsername(3))
                    .expectErrorMessage("invalid input")
                    .verify(); // subscribe
    }

    @Test
    public void errorTest4() {
        StepVerifier.create(getUsername(3))
                    .consumeErrorWith(ex -> {
                        Assertions.assertEquals(RuntimeException.class, ex.getClass());
                        Assertions.assertEquals("invalid input", ex.getMessage());
                    })
                    .verify(); // subscribe
    }

}
