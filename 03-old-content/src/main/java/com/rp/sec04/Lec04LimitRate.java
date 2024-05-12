package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {

    public static void main(String[] args) {

        Flux.range(1, 1000) // 175
                .log()
                .limitRate(100, 0) // 75%
                .subscribe(Util.subscriber());



    }


}
