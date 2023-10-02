package com.example.actuatordemo.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFunctionCounterWithMeterBinderConfig {

    @Bean
    public MeterBinder myFunctionCounterWithMeterBinder(MyHttpRequestManagerWithoutMicrometer myManager) {
        return new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry meterRegistry) {
                FunctionCounter.builder("my.function.counter.with.meterbinder", myManager, m -> {
                    return m.getCount();
                }).register(meterRegistry);
            }
        };
    }
}
