package com.rp.sec10;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec04RetryWhenAdvanced {

    public static void main(String[] args) {

        orderService(Util.faker().business().creditCardNumber())
                .retryWhen(Retry.from(
                     flux -> flux
                                .doOnNext(rs -> {
                                    System.out.println(rs.totalRetries());
                                    System.out.println(rs.failure());
                                })
                                .handle((rs, synchronousSink) -> {
                                    if(rs.failure().getMessage().equals("500"))
                                        synchronousSink.next(1);
                                    else
                                        synchronousSink.error(rs.failure());
                                })
                                .delayElements(Duration.ofSeconds(1))
                ))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);


    }


    // order service
    private static Mono<String> orderService(String ccNumber){
        return Mono.fromSupplier(() -> {
            processPayment(ccNumber);
            return Util.faker().idNumber().valid();
        });
    }

    // payment service
    private static void processPayment(String ccNumber){
        int random = Util.faker().random().nextInt(1, 10);
        if(random < 8)
            throw new RuntimeException("500");
        else if(random < 10)
            throw new RuntimeException("404");
    }

}
