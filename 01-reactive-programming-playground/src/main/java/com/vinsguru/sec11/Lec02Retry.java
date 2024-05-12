package com.vinsguru.sec11;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
    retry operator simply resubscribes when it sees error signal.
 */
public class Lec02Retry {

    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {

        demo2();

        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getCountryName()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1))
                             .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                             .onRetryExhaustedThrow((spec, signal) -> signal.failure())
                )
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                       if (atomicInteger.incrementAndGet() < 5) {
                           throw new RuntimeException("oops");
                       }
                       return Util.faker().country().name();
                   })
                   .doOnError(err -> log.info("ERROR: {}", err.getMessage()))
                   .doOnSubscribe(s -> log.info("subscribing"))
                ;
    }

}
