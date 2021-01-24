package com.rp.sec07;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec01Demo {

    public static void main(String[] args) {


        Flux.create(fluxSink -> {
            for (int i = 1; i < 501; i++) {
                fluxSink.next(i);
                System.out.println("Pushed : " + i);
            }
            fluxSink.complete();
        })
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(i -> {
                Util.sleepMillis(10);
            })
            .subscribe(Util.subscriber());


        Util.sleepSeconds(60);

    }

}
