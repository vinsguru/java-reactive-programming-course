package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

/*
    To create a range of items based on the given start and count
 */
public class Lec05FluxRange {

    public static void main(String[] args) {

        Flux.range(3, 10)
                .subscribe(Util.subscriber());

        // assignment - generate 10 random first names
        Flux.range(1, 10)
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

    }

}
