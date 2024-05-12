package com.vinsguru.sec13.client;

import com.vinsguru.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

// just for demo
public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getBook() {
        return this.httpClient.get()
                              .uri("/demo07/book")
                              .responseContent()
                              .asString()
                              .startWith(RateLimiter.limitCalls())
                              .contextWrite(UserService.userCategoryContext())
                              .next();
    }

}
