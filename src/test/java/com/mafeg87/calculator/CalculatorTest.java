package com.mafeg87.calculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.mafeg87.calculator.*;

public class CalculatorTest {
     private Calculator calculator = new Calculator();
     
     @Test
     public void testSum() {
          assertEquals(5, calculator.sum(2, 3));
     }
}