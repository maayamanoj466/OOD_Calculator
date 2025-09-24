package calculator;

/**
 * Implements Calculator.
 * No fields as the abstractCalculator holds all fields
 */
public class SimpleCalculator extends AbstractCalculator {
  /**
   * Initialises the operating fields to nothing in the abstract class
   * Takes in no arguments because input method handles that.
   */
  public SimpleCalculator() {
    super();
  }

  @Override
  public Calculator input(char a) {
    invalidInput(a);

    if (finalOutput.isEmpty() &&
            (this.a == '+' || this.a == '-' || this.a == '*')) {
      throw new IllegalArgumentException("You cannot start with an operator");
    }

    if ((this.a == '+' || this.a == '-' || this.a == '*')
            && (this.finalOutput.charAt(finalOutput.length() - 1) == '-'
            || this.finalOutput.charAt(finalOutput.length() - 1) == '+'
            || this.finalOutput.charAt(finalOutput.length() - 1) == '*')) {
      throw new IllegalArgumentException("There cannot be two consecutive operators");
    }

    digitEquals();

    invalidOperand1();
    invalidOperand2();

    computation();

    cReset();

    return this;
  }

  protected void currentAdd() {
    if (a == '+') {
      operatorLogic("+");
    }
  }

  protected void currentSubtract() {
    if (a == '-') {
      operatorLogic("-");
    }
  }

  protected void currentMultiply() {
    if (a == '*') {
      operatorLogic("*");
    }
  }

  protected void currentEquals() {
    long num1;
    long num2;
    long ans;
    if ((a == '=' && (finalOutput.indexOf("+") == finalOutput.length() - 1))
            || (a == '=' && (finalOutput.indexOf("-") == finalOutput.length() - 1))
            || (a == '=' && (finalOutput.indexOf("*") == finalOutput.length() - 1))) {
      throw new IllegalArgumentException("cannot have an operator just before =");

    } else if (a == '='
            && (finalOutput.contains("-")
            || finalOutput.contains("+")
            || finalOutput.contains("*"))) {
      this.finalOutput = this.finalOutput + a;
      if (finalOutput.indexOf(finalOutput.length() - 1) == '+'
              || finalOutput.indexOf(finalOutput.length() - 1) == '-'
              || finalOutput.indexOf(finalOutput.length() - 1) == '*') {
        throw new IllegalArgumentException("operator without a number after is not allowed");
      }
      switch (currentOpp) {
        case "+":
          num1 = (long) Integer.parseInt(currentFinal);
          num2 = (long) Integer.parseInt(
                  finalOutput.substring(finalOutput.indexOf("+") + 1,
                          finalOutput.length() - 1));
          ans = num1 + num2;
          if (ans >= newMax || ans <= newMin) {
            currentFinal = "0";
          } else {
            currentFinal = Long.toString(ans);
          }
          finalOutput = currentFinal;
          currentOpp = "=";
          break;
        case "-":
          num1 = (long) Integer.parseInt(currentFinal);
          if (finalOutput.charAt(0) == '-') {
            String tempFinal = finalOutput.substring(1);
            num2 = (long) Integer.parseInt(
                    tempFinal.substring(tempFinal.indexOf("-") + 1,
                            tempFinal.length() - 1));
          } else {
            num2 = (long) Integer.parseInt(
                    finalOutput.substring(finalOutput.indexOf("-") + 1,
                            finalOutput.length() - 1));
          }
          ans = num1 - num2;
          if (ans >= newMax || ans <= newMin) {
            currentFinal = "0";
          } else {
            currentFinal = Long.toString(ans);
          }
          finalOutput = currentFinal;
          currentOpp = "=";
          break;
        case "*":
          if (currentFinal.charAt(0) == '-') {
            String tempFinal = currentFinal.substring(1);
            num1 = (long) Integer.parseInt(tempFinal);
            num2 = (long) Integer.parseInt(
                    finalOutput.substring(finalOutput.indexOf("*") + 1,
                            finalOutput.length() - 1));
            ans = num1 * num2;
            if (ans >= newMax || ans <= newMin) {
              currentFinal = "0";
            } else {
              currentFinal = Long.toString(ans);
            }
            finalOutput = "-" + currentFinal;
            currentOpp = "=";
          } else {
            num1 = (long) Integer.parseInt(currentFinal);
            num2 = (long) Integer.parseInt(
                    finalOutput.substring(finalOutput.indexOf("*") + 1,
                            finalOutput.length() - 1));
            ans = num1 * num2;
            if (ans >= newMax || ans <= newMin) {
              currentFinal = "0";
            } else {
              currentFinal = Long.toString(ans);
            }
            finalOutput = currentFinal;
            currentOpp = "=";
          }
          break;
        default: // nothing happens because no other operators will be encountered
      }
    }

    if (finalOutput.charAt(finalOutput.length() - 1) == '=') {
      finalOutput = finalOutput.substring(0, finalOutput.length() - 1);
    }
  }
}
