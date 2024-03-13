package com.patterns.patterns.math_chainOfResponsibility;

import org.springframework.stereotype.Component;

@Component
public class Decrement implements MathOperation {
    @Override
    public String getAction() {
        return "decrement";
    }

    @Override
    public int calculate(int a, int b) {
        return a - b;
    }
}
