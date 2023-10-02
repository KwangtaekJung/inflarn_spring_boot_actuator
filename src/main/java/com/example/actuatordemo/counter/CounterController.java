package com.example.actuatordemo.counter;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counter")
@RequiredArgsConstructor
public class CounterController {

    private final MyHttpRequestManager myHttpRequestManager;

    private final MyHttpRequestManagerWithoutMicrometer myManager;

    private final MeterRegistry meterRegistry;

    @GetMapping("/req")
    public String req() {
        myHttpRequestManager.increase();

        myManager.increase();

        Counter.builder("my.http.request.direct.registry")
                .register(meterRegistry)
                .increment();

        return "ok";
    }

    @Counted("my.http.counted.counter")
    @GetMapping("/req2")
    public String req2() {
        return "ok";
    }
}
