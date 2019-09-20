package ua.alexch.task.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DBTestDataSetup {
    private List<String> firstNames = Arrays.asList("Zoey", "Emma", "Amelia", "Lily", "Mia", "Chloe", "Scarlett",
            "Elizabeth", "Mary", "Sophia", "Isaac", "Michael", "Alex", "Oliver", "William", "Jack", "Tom", "James",
            "Bob", "John");

    private List<String> lastNames = Arrays.asList("Cooper", "Simmons", "Smith", "Hall", "Brown", "Davis", "Miller",
            "Jackson", "White", "Martin", "Thompson", "Garcia", "Robinson", "Clark", "Young", "King", "Scott", "Adams",
            "Parker", "Collins");

    private List<String> courseList = Arrays.asList("Math/Natural sciences", "Physics/Physical sciences",
            "Astronomy/Space science", "Programming/Computer science", "Psychology/Medicine and health",
            "Philosophy/Humanitarian sciences", "Archeology/Humanitarian sciences", "Economics/Social sciences",
            "Chemistry/Natural sciences", "Biology/Natural sciences");

    private List<Integer> groupIds;
    private List<Integer> courseIds;
    private List<Integer> studentIds;

    private DBManager manager;

    public DBTestDataSetup() {
        groupIds = new ArrayList<>();
        courseIds = new ArrayList<>();
        studentIds = new ArrayList<>();
        manager = new DBManager();
    }

    public void setupTestData() {
        addGroups();
        addCourses();
        addStudents();
        assignStudents();
        enrollStudents();
    }

    public void addGroups() {
        int groupId;
        for (int i = 0; i < 10; i++) {
            groupId = manager.addGroup(generateGroupName());
            groupIds.add(groupId);
        }

        System.out.println("Groups added");
    }

    public void addCourses() {
        int courseId;
        for (String courseData : courseList) {
            String[] data = courseData.split("/");
            courseId = manager.addCourse(data[0], data[1]);
            courseIds.add(courseId);
        }

        System.out.println("Courses added");
    }

    public void addStudents() {
        int studentId;

        for (int i = 0; i < 200; i++) {
            studentId = manager.addStudent(getRandomName(firstNames), getRandomName(lastNames));
            studentIds.add(studentId);
        }

        System.out.println("Students added");
    }

    public void assignStudents() {
        manager.assignStudentsToGroups(studentIds, groupIds);
        System.out.println("All students are assigned");
    }

    public void enrollStudents() {
        Random random = new Random();
        int courseIdsIndex = Integer.MAX_VALUE;

        for (int studentId : studentIds) {
            for (int i = random.nextInt(3) + 1; i > 0; i--) {
                if (courseIdsIndex >= courseIds.size()) {
                    Collections.shuffle(courseIds);
                    courseIdsIndex = 0;
                }

                manager.enrollStudentToCourse(studentId, courseIds.get(courseIdsIndex));
                courseIdsIndex++;
            }
            courseIdsIndex = Integer.MAX_VALUE;
        }

        System.out.println("All students are enrolled");
    }

    private String generateGroupName() {
        Random random = new Random();
        return new StringBuilder().append((char) (random.nextInt(26) + 'a')).append((char) (random.nextInt(26) + 'a'))
                .append('-').append(random.nextInt(90) + 10).toString();
    }

    private String getRandomName(List<String> names) {
        Random random = new Random();
        int index = random.nextInt(names.size());
        return names.get(index);
    }
}
