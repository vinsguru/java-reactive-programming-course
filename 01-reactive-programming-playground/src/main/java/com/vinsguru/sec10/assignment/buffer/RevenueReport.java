package com.vinsguru.sec10.assignment.buffer;

import java.time.LocalTime;
import java.util.Map;

public record RevenueReport(LocalTime time,
                            Map<String, Integer> revenue) {
}
