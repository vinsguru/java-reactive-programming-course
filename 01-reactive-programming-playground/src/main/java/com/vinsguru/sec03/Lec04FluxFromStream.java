package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

/*
    To create flux from stream
 */
public class Lec04FluxFromStream {

    public static void main(String[] args) {

        var list = List.of(1,2,3,4);
        var stream = list.stream();

//        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);

        var flux = Flux.fromStream(list::stream);

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));




    }

}
