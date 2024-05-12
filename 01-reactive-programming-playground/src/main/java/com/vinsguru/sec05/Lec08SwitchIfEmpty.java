package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

/*
    Similar to error handling.
    Handling empty!
 */
public class Lec08SwitchIfEmpty {

    public static void main(String[] args) {

        /*
            postgres + redis
         */

        Flux.range(1, 10)
            .filter(i -> i > 10)
            .switchIfEmpty(fallback())
            .subscribe(Util.subscriber());


    }

    private static Flux<Integer> fallback(){
        return Flux.range(100, 3);
    }

}
