package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    Collect items based on given internal / size
 */
public class Lec01Buffer {

    public static void main(String[] args) {

        demo4();


        Util.sleepSeconds(60);

    }

    private static void demo1() {
        eventStream()
                .buffer() // int-max value or the source has to complete
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        eventStream()
                .buffer(3) // every 3 items
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        eventStream()
                .buffer(Duration.ofMillis(500)) // every 500ms
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1)) // every 3 items or max 1 second
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                  // .take(10)
                  // .concatWith(Flux.never())
                   .map(i -> "event-" + (i + 1));
    }

}
