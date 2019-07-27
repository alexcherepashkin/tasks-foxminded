package ua.alexch.task.anagram;

public class ClientAnagram {

    public static void main(String[] args) {
        TextTransformer textReverser = new TextReverser();
        TextTransformer eachWord = new EachWordTransformer(textReverser);
        String result = eachWord.transform("1qwer ty!");
        System.out.println(result);
    }
}
