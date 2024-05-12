package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

/*
    To create a flux & emit items programmatically
 */
public class Lec01FluxCreate {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
                String country;
                do {
                    country = Util.faker().country().name();
                    fluxSink.next(country);
                } while (!country.equalsIgnoreCase("canada"));
                fluxSink.complete();
            })
            .subscribe(Util.subscriber());


    }

}
