package ua.alexch.task.collections;

public class CachingCharCounterDecorator implements CharCounter {
    private final Cache cache;
    private final CharCounter counter;

    CachingCharCounterDecorator(CharCounter counter, Cache cache) {
        this.cache = cache;
        this.counter = counter;
    }

    @Override
    public String count(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Null is an inappropriate argument");
        }

        if (cache.isContain(text)) {
            return cache.getValue(text);

        } else {
            String result = counter.count(text);
            cache.put(text, result);
            return result;

        }
    }
}
