package com.rp.sec04.helper;

import reactor.core.publisher.Flux;

public class UserService {

    public static Flux<User> getUsers(){
        return Flux.range(1, 2)
                    .map(i -> new User(i));
    }


}
