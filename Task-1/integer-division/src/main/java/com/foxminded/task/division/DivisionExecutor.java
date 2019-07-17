package com.foxminded.task.division;

public class DivisionExecutor {

    private static final String SEPARATOR = "|";
    private StringBuilder result;
    private StringBuilder quotient;

    public String makeDivision(int dividend, int divisor) {
        result = new StringBuilder();

        if (dividend < 0 | divisor < 0) {
            result.append(dividend + " / " + divisor + " = " + dividend / divisor);

        } else if (divisor == 0) {
            throw new ArithmeticException("Division error, divisor is equal to zero!");

        } else {
            calculate(dividend, divisor);
        }
        return result.toString();
    }

    private void calculate(int dividend, int divisor) {
        quotient = new StringBuilder();
        String[] dividendDigits = Integer.toString(dividend).split("");
        StringBuilder firstOperandBuilder = new StringBuilder();
        int firstOperand;
        int secondOperand;
        int quotientDigit;
        int remainder;

        for (int i = 0; i < dividendDigits.length; i++) {
            firstOperandBuilder.append(dividendDigits[i]);
            firstOperand = Integer.parseInt(firstOperandBuilder.toString());

            if (firstOperand >= divisor | dividend < divisor) {
                quotientDigit = findQuotientDigit(firstOperand, divisor);
                quotient.append(quotientDigit);
                secondOperand = quotientDigit * divisor;
                remainder = firstOperand - secondOperand;

                String firstOperandView = String.format("%" + (i + 2) + "s", "_" + firstOperand);
                result.append(firstOperandView).append("\n");

                String secondOperandView = String.format("%" + (i + 2) + "s", secondOperand);
                result.append(secondOperandView).append("\n");

                String underLineView = String.format("%" + (i + 2) + "s",
                        assembleCharString(Integer.toString(secondOperand).length(), '-'));
                result.append(underLineView).append("\n");

                firstOperandBuilder.replace(0, firstOperandBuilder.length(), Integer.toString(remainder));
                firstOperand = Integer.parseInt(firstOperandBuilder.toString());
            } else if (i >= Integer.toString(divisor).length()) {
                quotient.append("0");
            }

            if (i == dividendDigits.length - 1) {
                result.append(String.format("%" + (i + 2) + "s", firstOperand)).append("\n");
            }
        }
        modifyResultForView(dividend, divisor);
    }

    private int findQuotientDigit(int firstOperand, int divisor) {
        int digit = 0;
        while (firstOperand >= divisor * (digit + 1)) {
            digit++;
        }
        return digit;
    }

    private String assembleCharString(int numberOfSymbols, char symbol) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            string.append(symbol);
        }
        return string.toString();
    }

    private void modifyResultForView(int dividend, int divisor) {
        int[] index = new int[3];
        for (int i = 0, j = 0; i < result.length() & j < 3; i++) {
            if (result.charAt(i) == '\n') {
                index[j] = i;
                j++;
            }
        }
        int tab = Integer.toString(dividend).length() + 1 - index[0];
        result.insert(index[2], assembleCharString(tab, ' ') + SEPARATOR + quotient.toString());
        result.insert(index[1], assembleCharString(tab, ' ') + SEPARATOR + assembleCharString(quotient.length(), '-'));
        result.insert(index[0], SEPARATOR + divisor);
        result.replace(1, index[0], Integer.toString(dividend));
    }
}
