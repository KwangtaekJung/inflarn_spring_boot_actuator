package com.example.actuatordemo.timer;

import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FunctionTimerConfigWithMeterBind {

    @Bean
    public MeterBinder myTimerMeterBinder(MyTimerManager myTimerManager) {
        return new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry meterRegistry) {
                FunctionTimer functionTimer = FunctionTimer.builder("my.timer5",
                                myTimerManager,
                                value -> {
                                    return value.getCount();
                                },
                                value -> {
                                    return value.getTotalTime();
                                },
                                TimeUnit.SECONDS)
                        .register(meterRegistry);
            }
        };
    }
}
