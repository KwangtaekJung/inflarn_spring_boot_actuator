package com.example.actuatordemo.timer;


import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timer")
@RequiredArgsConstructor
public class TimerController {

    private final Timer myTimer;
    private final MeterRegistry meterRegistry;

    @GetMapping("/timer")
    public String timer() {
        myTimer.record(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        return "ok";
    }

    @GetMapping("/timer2")
    public String timer2() throws InterruptedException {
        Timer.Sample sample = Timer.start(meterRegistry);

        // biz logic...
        test(sample);

        return "ok";
    }

    private void test(Timer.Sample sample) throws InterruptedException {
        Thread.sleep(2000);

        sample.stop(meterRegistry.timer("my.timer2"));
    }

    @Timed("my.timer3")
    @GetMapping("/timer3/{sleepSeconds}")
    public String timer3(@PathVariable("sleepSeconds") int sleepSeconds) throws InterruptedException {

        Thread.sleep(sleepSeconds * 1000);

        return "ok";
    }
}
