package com.rp.sec02;

import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {

    public static void main(String[] args) {


        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4);

        Flux<Integer> evenFlux = integerFlux.filter(i -> i % 2 == 0);

        integerFlux
                .subscribe(i -> System.out.println("Sub 1 : " + i));

        evenFlux
                .subscribe(i -> System.out.println("Sub 2 : " + i));


    }

}
