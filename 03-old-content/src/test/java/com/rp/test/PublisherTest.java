package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class PublisherTest {

    // test publisher - pipeline test

    @Test
    public void test1(){

        TestPublisher<Integer> testPublisher = TestPublisher.create();
        Flux<Integer> flux = testPublisher.flux();
        process(flux);
        StepVerifier.create(flux)
                .then(() -> testPublisher.assertWasSubscribed())
                .then(() -> testPublisher.emit(0, 1, 2))
                .expectNextCount(4)
              //  .then(() -> testPublisher.assertWasCancelled())
              //  .expectNext(1, 4, 9)
                .verifyComplete();

    }

    @Test
    public void test2(){

        TestPublisher<Integer> testPublisher = TestPublisher.create();
        StepVerifier.create(testPublisher.flux())
                .then(() -> testPublisher.next(0, 1, 2))
                .expectNext(1, 4, 9)
                .as("first-round")
                .then(() -> testPublisher.emit(0, 1, 3))
                .expectNext(1, 4, 9)
                .as("second-round")
                .expectComplete()
                .verify();

    }

    @Test
    public void test3(){
        TestPublisher<Integer> testPublisher = TestPublisher.create();
        StepVerifier.create(testPublisher.flux())
                .then(() -> testPublisher.assertWasSubscribed()) // why not subscriber test cases like flux concat
                .then(() -> testPublisher.emit(0, 1, 2))
                .expectNext(1, 4, 9)
                .expectComplete()
                .verify();

    }

    // complex business logic
    private void process(Flux<Integer> flux){
        flux
                .map(i -> i + 1)
                .map(i -> i * i)
                .take(3)
                .subscribe(); // db / log files
    }


}
