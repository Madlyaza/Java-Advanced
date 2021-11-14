package com.Java.Calculator.config;

import com.Java.Calculator.bean.Infix;
import com.Java.Calculator.bean.InputReader;
import com.Java.Calculator.bean.Postfix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config
{
    @Bean
    public Infix infix()
    {
        return new Infix();
    }

    @Bean
    public Postfix postfix() { return new Postfix(); }

    @Bean
    public InputReader inputReader()
    {
        return new InputReader();
    }
}
