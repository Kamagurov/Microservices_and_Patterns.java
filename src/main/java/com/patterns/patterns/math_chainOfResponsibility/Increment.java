package com.patterns.patterns.math_chainOfResponsibility;

import org.springframework.stereotype.Component;

@Component
public class Increment implements MathOperation {
    @Override
    public String getAction() {
        return "inсrement";
    }

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
