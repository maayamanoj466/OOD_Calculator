package calculator;

/**
 * Calculator interface that contains only 2 methods.
 * Input method handles the input and operations.
 * getResult method returns final result after calculations
 */
public interface Calculator {

  /**
   * Takes in an input of a character and computes what the output should be.
   * Handles invalid inputs, overflow and calculations.
   *
   * @param a represents the singular character input into the calculator
   * @return the calculator with appropriate result (whether the string so far or computed result)
   */
  public Calculator input(char a);

  /**
   * Returns the result after computation.
   * A string of inputs so far or a calculated result.
   *
   * @return string of the result.
   */
  public String getResult();


}