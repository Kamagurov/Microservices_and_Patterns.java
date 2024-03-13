package com.patterns.patterns.math_chainOfResponsibility;

public interface MathOperation {

    public String getAction();

    public int calculate(int a, int b);
}
