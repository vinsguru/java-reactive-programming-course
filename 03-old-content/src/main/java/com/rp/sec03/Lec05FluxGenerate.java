package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            System.out.println("emitting");
            synchronousSink.next(Util.faker().country().name()); // 1
            //synchronousSink.error(new RuntimeException("oops"));
        })
        .take(2)
        .subscribe(Util.subscriber());


    }

}
