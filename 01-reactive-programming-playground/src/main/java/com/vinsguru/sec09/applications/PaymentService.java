package com.vinsguru.sec09.applications;

import reactor.core.publisher.Mono;

import java.util.Map;

/*
    Just for demo.
    Imagine payment-service, as an application, has an endpoint.
    This is a client class to make a call to the endpoint (IO request).
 */
public class PaymentService {

    private static final Map<Integer, Integer> userBalanceTable = Map.of(
            1, 100,
            2, 200,
            3, 300
    );

    public static Mono<Integer> getUserBalance(Integer userId) {
        return Mono.fromSupplier(() -> userBalanceTable.get(userId));
    }

}
