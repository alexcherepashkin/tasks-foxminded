package ua.alexch.task.anagrams;

import java.util.Scanner;

public class AnagramClient {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String textInput = input.nextLine();
        input.close();
        Anagram anagram = new Anagram();
        String result = anagram.reverseAll(textInput);
        System.out.print(result);
    }
}
