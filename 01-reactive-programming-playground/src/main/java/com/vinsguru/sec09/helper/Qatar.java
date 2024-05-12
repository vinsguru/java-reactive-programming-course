package com.vinsguru.sec09.helper;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

// to represent the client class to call remote service
public class Qatar {

    private static final String AIRLINE = "Qatar";

    public static Flux<Flight> getFlights(){
        return Flux.range(1, Util.faker().random().nextInt(3, 5))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(300, 800)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(400, 900)))
                .transform(Util.fluxLogger(AIRLINE));
    }

}
