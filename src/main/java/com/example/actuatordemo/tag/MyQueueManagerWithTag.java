package com.example.actuatordemo.tag;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyQueueManagerWithTag {

    private final MeterRegistry meterRegistry;

    public void push() {
        Counter.builder("my.queue.counter")
                .tag("type", "push")
                .tag("class", this.getClass().toString())
                .register(meterRegistry)
                .increment();
    }

    public void pop() {
        Counter.builder("my.queue.counter")
                .tag("type", "pop")
                .tag("class", this.getClass().toString())
                .register(meterRegistry)
                .increment();
    }
}
