package ua.alexch.task.java8api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class RacersLogReader extends TextReader {

    @Override
    public List<String> readLineByLine(String fileName) {
        try {
            data = Files.lines(Paths.get(new File(fileName).getAbsolutePath())).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("\"" + e.getMessage() + "\"" + " - is absent today:P");
        }

        return data;
    }
}
