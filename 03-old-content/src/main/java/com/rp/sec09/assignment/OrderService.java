package com.rp.sec09.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class OrderService {

    public static Flux<PurchaseOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(100))
                    .map(i -> new PurchaseOrder());
    }


}
