package com.example.actuatordemo.custommetrics;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class MyStockManager {

    public long getStockCount() {
        return System.currentTimeMillis();
    }
}
