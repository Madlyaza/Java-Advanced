package Test;

import com.Java.Calculator.bean.Postfix;
import com.Java.Calculator.config.Config;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class PostfixTest {
    Postfix postfix;

    @BeforeEach
    public void init() {
        this.postfix = new Postfix();
    }

    @Test
    @DisplayName("Postfix Normal + - * / calculations")
    void calculateNormal() {
        assertEquals(10, postfix.calculate("5 5 +"));
        assertEquals(0, postfix.calculate("5 5 -"));
        assertEquals(25, postfix.calculate("5 5 *"));
        assertEquals(1, postfix.calculate("5 5 /"));
    }

    @Test
    @DisplayName("Postfix longer calculations")
    void calculateLonger() {
        assertEquals(50, postfix.calculate("5 5 + 5 *"));
        assertEquals(15, postfix.calculate("5 5 5 + +"));
        assertEquals(55, postfix.calculate("5 5 5 + * 5 +"));
    }

    @Test
    @DisplayName("Postfix Error Throwing")
    void calculateError() {
        assertThrows(NumberFormatException.class, () -> {
            postfix.calculate("yesytased");
        });
    }
}