package ua.alexch.task.sql;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import ua.alexch.task.sql.model.Course;
import ua.alexch.task.sql.model.Group;
import ua.alexch.task.sql.model.Student;
import ua.alexch.task.sql.service.TableService;

public class DBSimpleApplication {

    public static void main(String[] args) {
        File startup = new File("startup.sql");
        File tear_down = new File("tear_down.sql");
        File db_config = new File("db_config.properties");

        DBConnectionFactory connectionFactory = new DBConnectionFactory(db_config);

        TableService tableService = new TableService(connectionFactory);
        tableService.setupTables(startup);

        DBTestDataSetup setupDB = new DBTestDataSetup(connectionFactory);
        setupDB.setupTestData();

        UniversityFacade university = new UniversityFacade(connectionFactory);
        Scanner scan = new Scanner(System.in);
        int option;

        List<Group> groups;
        List<Student> students;
        List<Course> courses;
        Long studentId;
        Long courseId;

        while (true) {
            System.out.println("\n-==MENU OPTIONS==-\n");
            
            System.out.println("(1) - Find all groups with less or equals student count\n"
                             + "(2) - Find all students related to course with given name\n"
                             + "(3) - Add new student\n"
                             + "(4) - Delete student by STUDENT_ID\n"
                             + "(5) - Add a student to the course (from a list)\n"
                             + "(6) - Remove the student from one of his or her courses\n"
                             + "(0) - Exit");

            System.out.print("\nPlease enter your option (0-6): ");
            option = scan.nextInt();

            switch (option) {
            case 1:
                groups = university.findGroupsWithLessStudentCount();
                groups.forEach(System.out::println);
                break;

            case 2:
                courses = university.findAllCourses();
                courses.forEach(System.out::println);
                System.out.print("\nEnter required COURSE_ID from the list: ");
                studentId = scan.nextLong();
                students = university.findStudentsByCourseId(studentId);
                students.forEach(System.out::println);
                break;

            case 3:
                System.out.print("\nEnter student FIRST_NAME: ");
                String fName = scan.next();
                System.out.print("Enter student LAST_NAME: ");
                String lName = scan.next();
                studentId = university.addStudent(fName, lName);
                System.out.printf("Student %s %s added with ID=%d!\n", fName, lName, studentId);
                break;

            case 4:
                System.out.print("\nEnter the STUDENT_ID to be deleted: ");
                studentId = scan.nextLong();
                university.deleteStudentById(studentId);
                System.out.printf("Student by ID=%d was deleted!\n", studentId);
                break;

            case 5:
                System.out.print("\nEnter the required STUDENT_ID: ");
                studentId = scan.nextLong();
                courses = university.findAllCourses();
                courses.forEach(System.out::println);
                System.out.print("\nEnter the COURSE_ID from the list: ");
                courseId = scan.nextLong();
                university.enrollStudentToCourse(studentId, courseId);
                System.out.println("The student was enrolled in the course!");
                break;

            case 6:
                System.out.print("\nEnter the required STUDENT_ID: ");
                studentId = scan.nextLong();
                courses = university.findCoursesByStudentId(studentId);
                courses.forEach(System.out::println);
                System.out.print("\nEnter the COURSE_ID from courses list the student is enrolled in: ");
                courseId = scan.nextLong();
                university.removeStudentFromCourse(studentId, courseId);
                System.out.println("The student was removed from course!");
                break;

            case 0:
                System.out.println("\nBye-bye!!");
                scan.close();
                tableService.setupTables(tear_down);
                return;

            default:
                System.out.println("\nDon't be a stupid=)");
            }

            System.out.println("\nEnter your next selection: ");
        }
    }
}
