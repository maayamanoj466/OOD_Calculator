package calculator;

import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;

import static org.junit.Assert.assertEquals;

/**
 * Abstract testing class that tests for the basic functions of the calculator
 * that are common amongst both calculator.
 * Such as basic operations.
 */
public abstract class AbstractCalculatorTest {
  Calculator abstractCalc;
  Calculator overflowOp1;
  Calculator overflowOp2;

  /**
   * Returns the appropriate calculator of each class that implements it.
   */
  public abstract Calculator getCalculator();

  /**
   * Returns the appropriate calculator required to test overflow for
   * operand 1.
   *
   * @return calculator type of the class implementing
   */
  public abstract Calculator getOverflowOp1();

  /**
   * Returns the appropriate calculator required to test overflow for
   * operand 2.
   *
   * @return calculator type of the class implementing
   */
  public abstract Calculator getOverflowOp2();

  @Before
  public void setup() {
    abstractCalc = getCalculator();
    overflowOp1 = getOverflowOp1();
    overflowOp2 = getOverflowOp2();
  }

  @Test
  public void testOnlyAdds() {
    //checks if default getResult without input is ""
    assertEquals("", abstractCalc.getResult());

    //add multiple numbers and end with an add
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('+');
    assertEquals("6+", abstractCalc.getResult());

    //add multiple numbers and end with a subtract
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('-');
    assertEquals("6-", abstractCalc.getResult());

    //add multiple numbers and end with a multiply
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('*');
    assertEquals("6*", abstractCalc.getResult());

    //addition with multiple digits per number
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('+');
    assertEquals("24+", abstractCalc.getResult());

    //test addition with equals to
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('=');
    assertEquals("4", abstractCalc.getResult());

    //test addition and subtraction
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('-');
    abstractCalc.input('2');
    assertEquals("4-2", abstractCalc.getResult());
    abstractCalc.input('=');
    assertEquals("2", abstractCalc.getResult());

    //test addition and multiplication
    abstractCalc.input('C').input('2');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('*');
    assertEquals("4*", abstractCalc.getResult());
    abstractCalc.input('2');
    abstractCalc.input('=');
    assertEquals("8", abstractCalc.getResult());
  }

  @Test
  public void testOnlySubtracts() {
    //subtract multiple numbers and end with an add
    abstractCalc.input('2');
    abstractCalc.input('-');
    abstractCalc.input('1');
    abstractCalc.input('-');
    abstractCalc.input('1');
    assertEquals("1-1", abstractCalc.getResult());
    abstractCalc.input('+');
    assertEquals("0+", abstractCalc.getResult());

    //input of c to restart calc
    //subtract multiple numbers and end with a subtract
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('-');
    abstractCalc.input('1');
    abstractCalc.input('-');
    abstractCalc.input('1');
    abstractCalc.input('-');
    assertEquals("0-", abstractCalc.getResult());

    //subtract multiple numbers and end with a multiply
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('-');
    abstractCalc.input('1');
    abstractCalc.input('-');
    abstractCalc.input('1');
    abstractCalc.input('*');
    assertEquals("0*", abstractCalc.getResult());

    //subtraction with multiple digits per number
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('2');
    abstractCalc.input('-');
    abstractCalc.input('2');
    abstractCalc.input('=');
    assertEquals("20", abstractCalc.getResult());

    //test subtraction and addition
    abstractCalc.input('C');
    abstractCalc.input('8');
    abstractCalc.input('-');
    abstractCalc.input('5');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('=');
    assertEquals("5", abstractCalc.getResult());

    //test subtraction and multiplication
    abstractCalc.input('C');
    abstractCalc.input('7');
    abstractCalc.input('-');
    abstractCalc.input('8');
    abstractCalc.input('*');
    abstractCalc.input('2');
    assertEquals("-1*2", abstractCalc.getResult());
    abstractCalc.input('=');
    assertEquals("-2", abstractCalc.getResult());

    //test subtraction and multiplication
    abstractCalc.input('C');
    abstractCalc.input('7');
    abstractCalc.input('-');
    abstractCalc.input('8');
    abstractCalc.input('+');
    abstractCalc.input('2');
    assertEquals("-1+2", abstractCalc.getResult());
    abstractCalc.input('=');
    assertEquals("1", abstractCalc.getResult());
  }

  @Test
  public void testNegative() {
    //addition which deals with a result of negative numbers
    abstractCalc.input('2');
    abstractCalc.input('-');
    abstractCalc.input('3');
    abstractCalc.input('+');
    abstractCalc.input('2');
    abstractCalc.input('+');
    assertEquals("1+", abstractCalc.getResult());

    //subtraction which deals with a result of negative numbers
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('-');
    abstractCalc.input('3');
    abstractCalc.input('-');
    abstractCalc.input('2');
    abstractCalc.input('+');
    assertEquals("-3+", abstractCalc.getResult());

    //subtraction which deals with a result of negative numbers
    abstractCalc.input('C');
    abstractCalc.input('2');
    abstractCalc.input('-');
    abstractCalc.input('3');
    abstractCalc.input('*');
    abstractCalc.input('2');
    abstractCalc.input('=');
    assertEquals("-2", abstractCalc.getResult());

    //result too big, result is 0
    abstractCalc.input('C');
    abstractCalc.input('2').input('*').input('1');
    abstractCalc.input('6').input('6').input('6').input('6').input('6').input('6');
    abstractCalc.input('6').input('6').input('6').input('=');
    assertEquals("0", abstractCalc.getResult());
  }

  @Test
  public void testEquals() {
    abstractCalc.input('2').input('+').input('3').input('=').input('5');
    assertEquals("5", abstractCalc.getResult());
    abstractCalc.input('C');
    abstractCalc.input('2').input('+').input('3').input('=').input('+');
    assertEquals("5+", abstractCalc.getResult());
    abstractCalc.input('C');
    abstractCalc.input('2').input('+').input('3').input('=').input('3').input('5');
    assertEquals("35", abstractCalc.getResult());
    abstractCalc.input('C');
    abstractCalc.input('6').input('+').input('5').input('=');
    assertEquals("11", abstractCalc.getResult());
    abstractCalc.input('C');
    abstractCalc.input('3').input('+').input('3').input('=').input('+');
    abstractCalc.input('5');
    abstractCalc.input('=');
    assertEquals("11", abstractCalc.getResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testingWrongInput() {
    //input that is division
    abstractCalc.input('/');
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOperandOneWrong() {
    //operand1 is too large (over max int limit)
    overflowOp1.input('1');
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOperandTwoWrong() {
    //operand1 is too large (over max int limit)
    overflowOp2.input('1');
  }


}