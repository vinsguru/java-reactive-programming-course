package com.vinsguru.tests;

import com.vinsguru.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 50);
    }

    private Flux<Integer> getRandomItems() {
        return Flux.range(1, 50)
                   .map(i -> Util.faker().random().nextInt(1, 100));
    }

    @Test
    public void rangeTest1() {
        StepVerifier.create(getItems())
                    .expectNext(1, 2, 3)
                    .expectNextCount(47)
                    .expectComplete()
                    .verify();
    }

    @Test
    public void rangeTest2() {
        StepVerifier.create(getItems())
                    .expectNext(1, 2, 3)
                    .expectNextCount(22)
                    .expectNext(26, 27, 28)
                    .expectNextCount(22)
                    .expectComplete()
                    .verify();
    }

    @Test
    public void rangeTest3() {
        StepVerifier.create(getRandomItems())
                    .expectNextMatches(i -> i > 0 && i < 101)
                    .expectNextCount(49)
                    .expectComplete()
                    .verify();
    }

    @Test
    public void rangeTest4() {
        StepVerifier.create(getRandomItems())
                    .thenConsumeWhile(i -> i > 0 && i < 101)
                    .expectComplete()
                    .verify();
    }

}
