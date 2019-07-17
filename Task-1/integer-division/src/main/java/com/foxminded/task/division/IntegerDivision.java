package com.foxminded.task.division;

public class IntegerDivision {

    public static void main(String[] args) {
        int number1 = 78945;
        int number2 = 4;

        DivisionExecutor division = new DivisionExecutor();
        System.out.println(division.makeDivision(number1, number2));
    }
}
