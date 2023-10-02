package com.example.actuatordemo.custommetrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyStockMeterBinderConfig {

    @Bean
    public MeterBinder myMeterBinder(MyStockManager myStockManager) {
        return new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry meterRegistry) {
                Gauge.builder("my.stock", myStockManager, m -> {
                            return m.getStockCount();
                        })
                        .register(meterRegistry);
            }
        };
    }
}
