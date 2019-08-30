package ua.alexch.task.java8api;

import java.util.ArrayList;
import java.util.List;

public abstract class TextReader {
    List<String> data;

    TextReader() {
        this.data = new ArrayList<>();
    }

    public abstract List<String> readLineByLine(String file);
}
