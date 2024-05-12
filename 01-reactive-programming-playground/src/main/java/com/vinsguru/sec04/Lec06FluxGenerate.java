package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
    Flux generate
    - invokes the given lambda expression again and again based on downstream demand.
    - We can emit only one value at a time
    - will stop when complete method is invoked
    - will stop when error method is invoked
    - will stop downstream cancels
 */
public class Lec06FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {


        Flux.generate(synchronousSink -> {
                log.info("invoked");
                synchronousSink.next(1);
                // synchronousSink.next(2);
                //synchronousSink.complete();
                synchronousSink.error(new RuntimeException("oops"));
            })
            .take(4)
            .subscribe(Util.subscriber());


    }

}
