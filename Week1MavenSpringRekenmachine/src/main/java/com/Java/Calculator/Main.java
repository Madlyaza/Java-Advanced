package com.Java.Calculator;

import com.Java.Calculator.bean.Infix;
import com.Java.Calculator.bean.InputReader;
import com.Java.Calculator.bean.Postfix;
import com.Java.Calculator.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        InputReader inputReader = context.getBean(InputReader.class);
        Infix infix = context.getBean(Infix.class);
        Postfix postfix = context.getBean(Postfix.class);

        while (true) {
            System.out.println("Voer de som in voor infix: ");
            String input = inputReader.readInput();

            System.out.println(infix.calculate(input));

//            System.out.println("Voer de som in voor postfix: ");
//            input = inputReader.readInput();
//
//            System.out.println(postfix.calculate(input));
        }
    }
}
