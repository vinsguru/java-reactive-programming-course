package com.bong.sec01;

import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {
        // Publisher
        Mono<Integer> mono = Mono.just(1);

        // Subscribe 하기 전까지는 아무런 일도 일어나지 않는다.
        System.out.println(mono);

        // Publisher를 구독(Subscribe) 함으로써 비로소 데이터 발행과 연산이 시작된다.
        mono.subscribe(i -> System.out.println("Received : " + i));
    }
}
