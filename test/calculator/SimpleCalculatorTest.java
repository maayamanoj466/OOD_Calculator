package calculator;

import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;

/**
 * test cases for SimpleCalculator.
 * Tests all operators, and multiple operators in one calculation,
 * and exceptions or overflowing.
 */
public class SimpleCalculatorTest extends AbstractCalculatorTest {
  Calculator simpleCalc;
  Calculator overflowOp1;
  Calculator overflowOp2;
  Calculator exception1;
  Calculator exceptionConsecutiveOperator;

  @Before
  public void setUp() {

    //testing exception thrown when 3+=
    exception1 = new SimpleCalculator();
    exception1.input('3').input('+');

    simpleCalc = new SimpleCalculator();

    exceptionConsecutiveOperator = new SimpleCalculator();
    exceptionConsecutiveOperator.input('1').input('+');

  }

  @Test(expected = IllegalArgumentException.class)
  public void testOperatorBeforeEquals() {
    //cannot have an operator just before =
    exception1.input('=');
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConsecutiveOperator() {
    //cannot have two consecutive operators
    exceptionConsecutiveOperator.input('+');
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFirstOperator() {
    //cannot have two consecutive operators
    simpleCalc.input('+');
  }

  @Test
  public void testDoubleEquals() {
    //entering equals to multiple times gives the same result
    simpleCalc.input('2').input('+').input('2');
    simpleCalc.input('=');
    assertEquals("4", simpleCalc.getResult());
    simpleCalc.input('=');
    assertEquals("4", simpleCalc.getResult());
  }


  @Override
  public Calculator getCalculator() {
    return new SimpleCalculator();
  }

  @Override
  public Calculator getOverflowOp2() {
    overflowOp2 = new SimpleCalculator();
    overflowOp2.input('1').input('+').input('1').input('1').input('1');
    overflowOp2.input('1').input('1').input('1').input('1');
    overflowOp2.input('1').input('1').input('1');
    return overflowOp2;
  }

  @Override
  public Calculator getOverflowOp1() {
    overflowOp1 = new SimpleCalculator();
    overflowOp1.input('1').input('1').input('1').input('1').input('1');
    overflowOp1.input('1').input('1').input('1').input('1').input('1');
    return overflowOp1;
  }
}