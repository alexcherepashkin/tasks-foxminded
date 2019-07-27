package ua.alexch.task.collections;

public interface Cache {
    boolean isContain(String key);

    String getValue(String key);

    void put(String key, String value);
}
