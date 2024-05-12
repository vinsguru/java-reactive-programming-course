package com.vinsguru.sec04.assignment;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

/*
    - do the work only when it is subscribed
    - do the work based on the demand
    - stop producing when subscriber cancels
    - produce only the requested items
    - file should be closed once done
 */
public interface FileReaderService {

    Flux<String> read(Path path);

}
