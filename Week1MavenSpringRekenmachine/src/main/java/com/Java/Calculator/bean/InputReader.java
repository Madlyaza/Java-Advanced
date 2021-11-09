package com.Java.Calculator.bean;

import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Profile("inputReader")
public class InputReader
{
    public String readInput()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            return input;
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }

        return null;
    }
}
