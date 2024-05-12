package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            System.out.println("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
           // fluxSink.complete();
            fluxSink.error(new RuntimeException("oops"));
            System.out.println("--completed");
        })
        .doOnComplete(() -> System.out.println("doOnComplete"))
        .doFirst(() -> System.out.println("doFirst"))
        .doOnNext(o -> System.out.println("doOnNext : " + o))
        .doOnSubscribe(s -> System.out.println("doOnSubscribe" + s))
        .doOnRequest(l -> System.out.println("doOnRequest : " + l))
        .doOnError(err -> System.out.println("doOnError : " + err.getMessage()))
        .doOnTerminate(() -> System.out.println("doOnTerminate"))
        .doOnCancel(() -> System.out.println("doOnCancel"))
        .doFinally(signal -> System.out.println("doFinally 1 : " + signal))
        .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o))
        .take(2)
                .doFinally(signal -> System.out.println("doFinally 2 : " + signal))
        .subscribe(Util.subscriber());


    }

}
