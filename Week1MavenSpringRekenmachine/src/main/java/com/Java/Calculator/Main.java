package com.Java.Calculator;

import com.Java.Calculator.bean.Infix;
import com.Java.Calculator.bean.InputReader;
import com.Java.Calculator.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        InputReader inputReader = context.getBean(InputReader.class);

        System.out.println("Voer de som in: ");
        String input = inputReader.readInput();

        Infix infix = context.getBean(Infix.class);
        System.out.println(infix.calculate(input));
    }
}
