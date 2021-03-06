package com.Java.Calculator.bean;

import org.springframework.context.annotation.Profile;

import java.util.Stack;

@Profile("postfixTest")
public class Postfix {
    public Integer calculate(String x) {
        String[] strArray = x.split(" ");
        Stack<Integer> operands = new Stack<>();

        for (String val : strArray) {
            switch (val) {
                case "+":
                case "-":
                case "*":
                case "/":
                    int right = operands.pop();
                    int left = operands.pop();
                    int value = 0;
                    switch (val) {
                        case "+":
                            value = left + right;
                            break;
                        case "-":
                            value = left - right;
                            break;
                        case "*":
                            value = left * right;
                            break;
                        case "/":
                            value = left / right;
                            break;
                        default:
                            break;
                    }
                    operands.push(value);
                    break;
                default:
                    operands.push(Integer.parseInt(val));
                    break;
            }
        }
        return operands.pop();
    }
}
