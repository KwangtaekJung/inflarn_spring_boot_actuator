package com.example.actuatordemo.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final MyQueueManagerWithTag myQueueManagerWithTag;

    @GetMapping("/push")
    public String push() {
        myQueueManagerWithTag.push();

        return "ok";
    }

    @GetMapping("/pop")
    public String pop() {
        myQueueManagerWithTag.pop();

        return "ok";
    }
}
