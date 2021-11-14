package com.Java.Calculator.bean;

import org.springframework.context.annotation.Profile;

import java.util.Stack;

@Profile("infixTest")
public class Infix
{
    public Integer calculate (String x)
    {
        Stack<String> operators = new Stack<String>();
        Stack<Integer> operands = new Stack<Integer>();

        boolean lastNum = false;
        String[] args = x.split("");
        for (String val:args)
        {
            if(val.trim().equals(""))
            {
                continue;
            }

            switch (val)
            {
                case "+":
                case "-":
                case "*":
                case "/":
                    operators.push(val);
                    lastNum = false;
                    break;
                default:
                    if(lastNum)
                    {
                        String num = operands.pop().toString();
                        String temp = num + val;
                        operands.push(Integer.parseInt(temp));
                        break;
                    }
                    lastNum = true;
                    operands.push(Integer.parseInt(val));

                    break;
            }
        }

        int right = operands.pop();
        int left = operands.pop();
        String operator = operators.pop();
        int value = 0;
        switch (operator)
        {
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
        return value;
    }
}
