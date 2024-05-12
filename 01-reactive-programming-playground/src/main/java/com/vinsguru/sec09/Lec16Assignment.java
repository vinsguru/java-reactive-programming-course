package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.applications.*;
import reactor.core.publisher.Mono;

import java.util.List;

/*
    Get all users and build 1 object as shown here.
    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders) {}
 */
public class Lec16Assignment {

    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders) {
    }

    public static void main(String[] args) {

        UserService.getAllUsers()
                   .flatMap(Lec16Assignment::getUserInformation)
                   .subscribe(Util.subscriber());

        Util.sleepSeconds(2);

    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                           PaymentService.getUserBalance(user.id()),
                           OrderService.getUserOrders(user.id()).collectList()
                   )
                   .map(t -> new UserInformation(user.id(), user.username(), t.getT1(), t.getT2()));
    }

}
