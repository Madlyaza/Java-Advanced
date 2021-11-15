package Test;

import com.Java.Calculator.bean.InputReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest
{

    @Test
    @DisplayName("InputReader testing input")
    void readInput()
    {
        String data = "Users Input";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        InputReader reader = new InputReader();
        assertTrue(reader.readInput().contains(data));
    }
}