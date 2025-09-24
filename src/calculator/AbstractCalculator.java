package calculator;

/**
 * Abstract Calculator represents the class with commonalities between smart
 * and simple calculator.
 * Similar fields include the input of char a, the final result to be produced is finalOutput,
 * the currentFinal which holds the most recent number calculated, and the currentOpp which
 * is the current operator passed in.
 */
public abstract class AbstractCalculator implements Calculator {
  protected char a;
  protected String finalOutput;
  protected String currentFinal;
  protected String currentOpp;

  /**
   * Initialises the operating fields to nothing.
   * Takes in no arguments because input method handles that.
   */
  public AbstractCalculator() {
    this.finalOutput = "";
    this.currentFinal = "";
    this.currentOpp = "";
  }

  long newMax = Integer.MAX_VALUE;
  long newMin = Integer.MIN_VALUE;

  /**
   * Takes in an input of a character and computes what the output should be.
   * Handles invalid inputs, overflow and calculations.
   *
   * @param a represents the singular character input into the calculator
   * @return the calculator with appropriate result (whether the string so far or computed result)
   */
  @Override
  public Calculator input(char a) {
    return null;
  }

  /**
   * Returns the result after computation.
   * A string of inputs so far or a calculated result.
   *
   * @return string of the result.
   */
  @Override
  public String getResult() {
    return this.finalOutput;
  }

  protected void invalidInput(char a) {
    if (Character.isDigit(a)
            || a == '-' || a == '+' || a == '*' || a == '='
            || a == 'C') {
      this.a = a;

    } else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  protected void digitEquals() {
    if (Character.isDigit(a)) {
      if (currentOpp.equals("=")) {
        finalOutput = "";
        currentOpp = "";
        currentFinal = "";
      }
      this.finalOutput = this.finalOutput + a;
    }
  }

  protected void invalidOperand1() {
    try {
      if (!finalOutput.contains("-")
              && !finalOutput.contains("+")
              && !finalOutput.contains("*")
              && !finalOutput.contains("=")) {
        Integer.parseInt(this.finalOutput);
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input, 1st operand too big");
    }
  }

  protected void invalidOperand2() {
    try {
      if (finalOutput.charAt(0) == '-') {
        String temp = finalOutput.substring(1);
        String[] digits = temp.split("[-+=C*]");
        for (int i = 0; i < digits.length; i++) {
          int parsedInt = Integer.parseInt(digits[i]);
        }
      } else {
        String[] digits = finalOutput.split("[-+=C*]");
        for (int i = 0; i < digits.length; i++) {
          int parsedInt = Integer.parseInt(digits[i]);
        }
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input, 2nd operand too big");
    }
  }

  protected void cReset() {
    if (a == 'C') {
      finalOutput = "";
      currentFinal = "";
      currentOpp = "";
    }
  }

  protected void computation() {
    try {
      this.currentAdd();
      this.currentSubtract();
      this.currentMultiply();
      this.currentEquals();
    } catch (NumberFormatException e) {
      finalOutput = "0";
    }
  }

  protected abstract void currentAdd();

  protected abstract void currentSubtract();

  protected abstract void currentMultiply();

  protected abstract void currentEquals();

  protected void operatorLogic(String opp) {
    this.finalOutput = this.finalOutput + a;
    int num1;
    int num2;
    long ans;
    if (currentFinal.isEmpty()) {
      currentFinal = finalOutput.substring(0, finalOutput.indexOf(opp));
      currentOpp = opp;
    } else if (currentOpp.equals("=")) {
      currentOpp = opp;
    } else {
      switch (currentOpp) {
        case "+":
          num1 = Integer.parseInt(currentFinal);
          num2 = Integer.parseInt(
                  finalOutput.substring(finalOutput.indexOf("+") + 1,
                          finalOutput.length() - 1));
          ans = num1 + num2;
          if (ans >= newMax || ans <= newMin) {
            currentFinal = "0";
          } else {
            currentFinal = Long.toString(ans);
          }
          finalOutput = currentFinal + opp;
          currentOpp = opp;
          break;
        case "-":
          num1 = Integer.parseInt(currentFinal);
          if (finalOutput.charAt(0) == '-') {
            String tempFinal = finalOutput.substring(1);
            num2 = Integer.parseInt(
                    tempFinal.substring(tempFinal.indexOf("-") + 1,
                            tempFinal.length() - 1));
          } else {
            num2 = Integer.parseInt(
                    finalOutput.substring(finalOutput.indexOf("-") + 1,
                            finalOutput.length() - 1));
          }
          ans = num1 - num2;
          if (ans >= newMax || ans <= newMin) {
            currentFinal = "0";
          } else {
            currentFinal = Long.toString(ans);
          }
          finalOutput = currentFinal + opp;
          currentOpp = opp;
          break;
        case "*":
          num1 = Integer.parseInt(currentFinal);
          num2 = Integer.parseInt(
                  finalOutput.substring(finalOutput.indexOf("*") + 1,
                          finalOutput.length() - 1));
          ans = (long) num1 * num2;
          if (ans >= newMax || ans <= newMin) {
            currentFinal = "0";
          } else {
            currentFinal = Long.toString(ans);
          }
          finalOutput = currentFinal + opp;
          currentOpp = opp;
          break;
        default: // nothing happens because no other operators will be encountered
      }

    }
  }
}
