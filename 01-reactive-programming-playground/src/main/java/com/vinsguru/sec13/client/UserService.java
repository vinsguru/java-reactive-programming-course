package com.vinsguru.sec13.client;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

// just for demo - could be a bean in real life
public class UserService {

    private static final Map<String, String> USER_CATEGORY = Map.of(
            "sam", "standard",
            "mike", "prime"
    );

    static Function<Context, Context> userCategoryContext() {
        return ctx -> ctx.<String>getOrEmpty("user")
                         .filter(USER_CATEGORY::containsKey)
                         .map(USER_CATEGORY::get)
                         .map(category -> ctx.put("category", category))
                         .orElse(Context.empty());
    }

}
