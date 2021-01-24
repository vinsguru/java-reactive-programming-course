package com.rp.sec08;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05CombineLatest {

    public static void main(String[] args) {

        Flux.combineLatest(getString(), getNumber(), (s, i) -> s+i)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }

    private static Flux<String> getString(){
        return Flux.just("A", "B", "C", "D")
                    .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getNumber(){
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(3));
    }


}
