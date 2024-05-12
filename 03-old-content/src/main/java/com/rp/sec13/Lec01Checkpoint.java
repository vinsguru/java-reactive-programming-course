package com.rp.sec13;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec01Checkpoint {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .checkpoint("cp1")
                .map(i -> i + i)
                .checkpoint("cp2")
                .map(i -> i / 2)
                .checkpoint("cp3")
                .flatMap(i -> Mono.just(i < 3 ? i : i /(5-i)))
                .checkpoint("cp4")
                .map(i -> i + 2)
                .checkpoint("cp5")
                .filter(i -> i % 2 == 0)
                .checkpoint("cp6")
                .subscribe(Util.onNext());





    }


}
