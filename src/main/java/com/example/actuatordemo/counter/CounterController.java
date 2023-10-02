package com.example.actuatordemo.counter;

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

    @GetMapping("/req")
    public String req() {
        myHttpRequestManager.increase();

        myManager.increase();

        return "ok";
    }
}
