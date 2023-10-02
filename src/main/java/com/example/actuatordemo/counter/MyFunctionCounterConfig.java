package com.example.actuatordemo.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyFunctionCounterConfig {

    private final MyHttpRequestManagerWithoutMicrometer myManager;

    private final MeterRegistry meterRegistry;

    @PostConstruct
    void init() {
        FunctionCounter.builder("my.function.counter", myManager, m -> {
            return m.getCount();
        }).register(meterRegistry);
    }
}
