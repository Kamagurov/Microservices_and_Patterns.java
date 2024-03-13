package com.patterns.patterns.math_chainOfResponsibility;

import org.springframework.stereotype.Component;

@Component
public class Multiply implements MathOperation {
    @Override
    public String getAction() {
        return "multiply";
    }

    @Override
    public int calculate(int a, int b) {
        return a * b;
    }
}
