package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    How to handle error in a reactive pipeline
    Flux.(...)
        ...
        ...
        ...
        ...
 */
public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {



    }

    // skip the error and continue
    private static void onErrorContinue(){
        Flux.range(1, 10)
            .map(i -> i == 5 ? 5 / 0 : i) // intentional
            .onErrorContinue((ex, obj) -> log.error("==> {}", obj, ex))
            .subscribe(Util.subscriber());
    }

    // in case of error, emit complete
    private static void onErrorComplete() {
        Mono.just(1)
            .onErrorComplete()
            .subscribe(Util.subscriber());
    }

    // when you want to return a hardcoded value / simple computation
    private static void onErrorReturn() {
        Mono.just(5)
            .map(i -> i == 5 ? 5 / 0 : i) // intentional
            .onErrorReturn(IllegalArgumentException.class, -1)
            .onErrorReturn(ArithmeticException.class, -2)
            .onErrorReturn(-3)
            .subscribe(Util.subscriber());
    }

    // when you have to use another publisher in case of error
    private static void onErrorResume() {
        Mono.error(new RuntimeException("oops"))
            .onErrorResume(ArithmeticException.class, ex -> fallback1())
            .onErrorResume(ex -> fallback2())
            .onErrorReturn(-5)
            .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 1000));
    }

}
