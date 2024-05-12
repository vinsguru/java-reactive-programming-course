package com.vinsguru.sec09.helper;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

// to represent the client class to call remote service
public class Emirates {

    private static final String AIRLINE = "Emirates";

    public static Flux<Flight> getFlights(){
        return Flux.range(1, Util.faker().random().nextInt(2, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200, 1000)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(300, 1000)))
                .transform(Util.fluxLogger(AIRLINE));
    }

}
