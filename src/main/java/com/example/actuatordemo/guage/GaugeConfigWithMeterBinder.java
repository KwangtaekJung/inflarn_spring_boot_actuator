package com.example.actuatordemo.guage;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GaugeConfigWithMeterBinder {

    @Bean
    public MeterBinder gaugeMeterBinder(QueueManager queueManager) {
        MeterBinder meterBinder =  new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry meterRegistry) {
                Gauge.builder("my.queue.size2", queueManager, queueManager -> {
                    return queueManager.getQueueSize();
                }).register(meterRegistry);
            }
        };

        return meterBinder;
    }
}
