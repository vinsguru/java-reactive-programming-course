package com.vinsguru.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/*
    If we do not have the terminal operator, then stream operators will not execute
 */
public class Lec01LazyStream {

    private static final Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);

    public static void main(String[] args) {

        Stream.of(1)
                .peek(i -> log.info("received : {}", i))
                .toList();


    }

    
}
