package com.example.actuatordemo.guage;

import org.springframework.stereotype.Service;

@Service
public class QueueManager {

    public long getQueueSize() {
        return System.currentTimeMillis();
    }
}
