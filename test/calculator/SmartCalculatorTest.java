package calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * test cases for SmartCalculator.
 * Tests all operators, and multiple operators in one calculation,
 * and exceptions or overflowing.
 */
public class SmartCalculatorTest extends AbstractCalculatorTest {
  Calculator smartCalc;

  @Before
  public void setUp() {
    smartCalc = new SmartCalculator();
    abstractCalc = new SmartCalculator();
  }

  @Test
  public void testStartPlus() {
    //if the first input is plus, ignores it.
    smartCalc.input('+');
    smartCalc.input('2').input('-').input('5').input('=');
    assertEquals("-3", smartCalc.getResult());
  }

  @Test
  public void testEquals() {
    //test double equals to
    smartCalc.input('2').input('+').input('3').input('=');
    assertEquals("5", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("8", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("11", smartCalc.getResult());

    //test double equals after multiple calculations
    smartCalc.input('4').input('-').input('2').input('=');
    assertEquals("2", smartCalc.getResult());
    smartCalc.input('+').input('3').input('=');
    assertEquals("5", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("8", smartCalc.getResult());

  }

  @Test
  public void testConsecutiveOperators() {
    //test on plus operator
    smartCalc.input('8');
    smartCalc.input('+');
    assertEquals("8+", smartCalc.getResult());
    smartCalc.input('-');
    assertEquals("8-", smartCalc.getResult());
    smartCalc.input('4');
    assertEquals("8-4", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("4", smartCalc.getResult());

    //test on minus operator
    smartCalc.input('C');
    smartCalc.input('8');
    smartCalc.input('-');
    assertEquals("8-", smartCalc.getResult());
    smartCalc.input('-');
    assertEquals("8-", smartCalc.getResult());
    smartCalc.input('4');
    assertEquals("8-4", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("4", smartCalc.getResult());

    //test on multiply operator
    smartCalc.input('8');
    smartCalc.input('*');
    assertEquals("8*", smartCalc.getResult());
    smartCalc.input('-');
    assertEquals("8-", smartCalc.getResult());
    smartCalc.input('4');
    assertEquals("8-4", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("4", smartCalc.getResult());

    //test on plus operator input
    smartCalc.input('8');
    smartCalc.input('+');
    assertEquals("8+", smartCalc.getResult());
    smartCalc.input('+');
    assertEquals("8+", smartCalc.getResult());
    smartCalc.input('4');
    assertEquals("8+4", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("12", smartCalc.getResult());

    //test on multiply operator input
    smartCalc.input('8');
    smartCalc.input('+');
    assertEquals("8+", smartCalc.getResult());
    smartCalc.input('*');
    assertEquals("8*", smartCalc.getResult());
    smartCalc.input('4');
    assertEquals("8*4", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("32", smartCalc.getResult());

    //test on multiply operator input
    smartCalc.input('2');
    smartCalc.input('3');
    smartCalc.input('+');
    assertEquals("23+", smartCalc.getResult());
    smartCalc.input('*');
    assertEquals("23*", smartCalc.getResult());
    smartCalc.input('4');
    assertEquals("23*4", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("92", smartCalc.getResult());
  }

  @Test
  public void testOperatorBeforeEquals() {
    //test on plus before equals
    smartCalc.input('2').input('+').input('=');
    assertEquals("4", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("6", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("8", smartCalc.getResult());

    //test on minus before equals
    smartCalc.input('2').input('-').input('=');
    assertEquals("0", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("-2", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("-4", smartCalc.getResult());

    //test on multiply before eqyals
    smartCalc.input('3').input('*').input('=');
    assertEquals("9", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("27", smartCalc.getResult());
    smartCalc.input('=');
    assertEquals("81", smartCalc.getResult());
  }


  @Override
  public Calculator getCalculator() {
    return new SmartCalculator();
  }

  @Override
  public Calculator getOverflowOp2() {
    overflowOp2 = new SmartCalculator();
    overflowOp2.input('1').input('+').input('1').input('1').input('1');
    overflowOp2.input('1').input('1').input('1').input('1');
    overflowOp2.input('1').input('1').input('1');
    return overflowOp2;
  }

  @Override
  public Calculator getOverflowOp1() {
    overflowOp1 = new SmartCalculator();
    overflowOp1.input('1').input('1').input('1').input('1').input('1');
    overflowOp1.input('1').input('1').input('1').input('1').input('1');
    return overflowOp1;
  }
}