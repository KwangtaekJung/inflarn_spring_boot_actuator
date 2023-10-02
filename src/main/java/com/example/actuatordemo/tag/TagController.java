package com.example.actuatordemo.tag;

import io.micrometer.core.annotation.Counted;
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

    @Counted(value = "my.counted.tag", extraTags = {"type1", "value1", "type2", "value2"})
    @GetMapping("/counted/push")
    public String counted_push() {

        return "ok";
    }

    @Counted(value = "my.counted.tag", extraTags = {"type3", "value3", "type4", "value4"})
    @GetMapping("/counted/pop")
    public String counted_pop() {

        return "ok";
    }
}
