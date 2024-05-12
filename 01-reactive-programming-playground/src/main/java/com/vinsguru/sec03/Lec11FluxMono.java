package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    To covert a Flux <--> Mono
 */
public class Lec11FluxMono {

    public static void main(String[] args) {

        monoToFlux();
        fluxToMono();

    }

    private static void fluxToMono(){
        var flux = Flux.range(1, 10);
        Mono.from(flux)
                .subscribe(Util.subscriber());
    }

    private static void monoToFlux(){
        var mono = getUsername(1);
        save(Flux.from(mono));
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty(); // null
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    private static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber());
    }

}
