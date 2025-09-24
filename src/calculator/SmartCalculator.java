package calculator;

/**
 * Implements Calculator.
 * Fields are the last operand which is the most recent operand to be calculated with,
 * previousActualOperator which holds the last operator to be calculated with,
 * and firstOperand which holds the first operand.
 * Has added features such as equals to returning new values and dealing with a starting operator.
 */
public class SmartCalculator extends AbstractCalculator {
  private String lastOperand;
  private String previousActualOperator;
  private String firstOperand;

  /**
   * Initialises the operating fields to nothing.
   * Takes in no arguments because input method handles that.
   */
  public SmartCalculator() {
    super();
    this.lastOperand = "";
    this.previousActualOperator = "";
    this.firstOperand = "";
  }

  @Override
  public Calculator input(char a) {
    invalidInput(a);

    if (finalOutput.isEmpty() && this.a == '+') {
      return this;
    } else {

      if ((this.a == '+' || this.a == '-' || this.a == '*')
              && (this.finalOutput.charAt(finalOutput.length() - 1) == '-'
              || this.finalOutput.charAt(finalOutput.length() - 1) == '+'
              || this.finalOutput.charAt(finalOutput.length() - 1) == '*')) {
        finalOutput = finalOutput.substring(0, finalOutput.length() - 1);
        currentOpp = "";
        currentFinal = "";
      }

      digitEquals();

      invalidOperand1();
      invalidOperand2();

      computation();

      cReset();

      return this;
    }
  }

  protected void currentAdd() {
    if (a == '+') {
      previousActualOperator = "+";
      operatorLogic("+");
    }
  }

  protected void currentSubtract() {
    if (a == '-') {
      previousActualOperator = "-";
      operatorLogic("-");
    }
  }

  protected void currentMultiply() {
    if (a == '*') {
      previousActualOperator = "*";
      operatorLogic("*");
    }
  }

  protected void currentEquals() {
    long num1;
    long num2;
    long ans;

    if (a == '=' && currentOpp.equals("=") && !lastOperand.isEmpty()) {
      num1 = Long.parseLong(currentFinal);
      num2 = Long.parseLong(lastOperand);
      ans = 0;

      // Repeat the last operation
      switch (previousActualOperator) {
        case "+":
          ans = num1 + num2;
          break;
        case "-":
          ans = num1 - num2;
          break;
        case "*":
          ans = num1 * num2;
          break;
        default: //do nothing because no other options of operators.
      }

      currentFinal = Long.toString(ans);
      finalOutput = currentFinal;
      return;
    }

    if ((a == '=' && (finalOutput.indexOf("+") == finalOutput.length() - 1))
            || (a == '=' && (finalOutput.indexOf("-") == finalOutput.length() - 1))
            || (a == '=' && (finalOutput.indexOf("*") == finalOutput.length() - 1))) {
      firstOperand = finalOutput.substring(0, finalOutput.length() - 1);
      switch (currentOpp) {
        case "+":
          ans = (long) Integer.parseInt(firstOperand) + (long) Integer.parseInt(firstOperand);
          if (ans >= newMax || ans <= newMin) {
            currentFinal = "0";
          } else {
            currentFinal = Long.toString(ans);
          }
          finalOutput = currentFinal;
          currentOpp = "=";
          lastOperand = firstOperand;
          break;
        case "-":
          ans = (long) Integer.parseInt(firstOperand) - (long) Integer.parseInt(firstOperand);
          if (ans >= newMax || ans <= newMin) {
            currentFinal = "0";
          } else {
            currentFinal = Long.toString(ans);
          }
          finalOutput = currentFinal;
          currentOpp = "=";
          lastOperand = firstOperand;
          break;
        case "*":
          ans = (long) Integer.parseInt(firstOperand) * (long) Integer.parseInt(firstOperand);
          if (ans >= newMax || ans <= newMin) {
            currentFinal = "0";
          } else {
            currentFinal = Long.toString(ans);
          }
          finalOutput = currentFinal;
          currentOpp = "=";
          lastOperand = firstOperand;
          break;
        default: //nothing, no other option
      }

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
          lastOperand = finalOutput.substring(finalOutput.indexOf("+") + 1,
                  finalOutput.length() - 1);
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
            lastOperand = tempFinal.substring(tempFinal.indexOf("-") + 1,
                    tempFinal.length() - 1);
          } else {
            num2 = (long) Integer.parseInt(
                    finalOutput.substring(finalOutput.indexOf("-") + 1,
                            finalOutput.length() - 1));
            lastOperand = finalOutput.substring(finalOutput.indexOf("-") + 1,
                    finalOutput.length() - 1);
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
            lastOperand = finalOutput.substring(finalOutput.indexOf("*") + 1,
                    finalOutput.length() - 1);
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
            lastOperand = finalOutput.substring(finalOutput.indexOf("*") + 1,
                    finalOutput.length() - 1);
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
