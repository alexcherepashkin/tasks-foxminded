package ua.alexch.task.collections;

public class CharCounterClient {

    public static void main(String[] args) {
        String text = "Kitty ))";
        Cache cache = new MapCache();
        CharCounter basicCounter = new BasicCharCounter();
        CharCounter counterDecorator = new CachingCharCounterDecorator(basicCounter, cache);
        System.out.println(counterDecorator.count(text));
    }
}
