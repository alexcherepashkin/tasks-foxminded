package com.foxminded.anagrams;

import java.util.Scanner;

public class Anagrams {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] text = input.nextLine().split(" ");
        input.close();
        Swap.reverse(text);
        for (String rev : text) {
            System.out.print(rev + " ");
        }
    }
}
