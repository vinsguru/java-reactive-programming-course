package com.bong.sec01;

import java.util.stream.Stream;

public class Lec01Stream {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1)      // Stream.of을 이용하여 값이 한 개(1)인 Stream을 생성
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return i * 2;
                });

        stream.forEach(System.out::println);
    }
}
