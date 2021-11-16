package Test;

import com.Java.Calculator.bean.Infix;
import com.Java.Calculator.config.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class InfixTest {
    Infix infix;

    @BeforeEach
    public void init() {
        this.infix = new Infix();
    }

    @Test
    @DisplayName("Infix Normal + - * / calculations")
    void calculate() {
        assertEquals(10, infix.calculate("5 + 5"));
        assertEquals(25, infix.calculate("5 * 5"));
        assertEquals(0, infix.calculate("5 - 5"));
        assertEquals(1, infix.calculate("5 / 5"));
    }

    @Test
    @DisplayName("Infix Error Throwing")
    void calculateError() {
        assertThrows(NumberFormatException.class, () -> {
            infix.calculate("yesytased");
        });
    }
}