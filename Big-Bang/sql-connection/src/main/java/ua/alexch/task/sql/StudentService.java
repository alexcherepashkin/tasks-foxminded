package ua.alexch.task.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentService {
    private static final String INSERT_ONE = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM students ORDER BY student_id";
    private static final String SELECT_BY_ID = "SELECT * FROM students WHERE student_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM students WHERE student_id=?";
    private static final String UPDATE_ALL = "UPDATE students SET group_id=? WHERE student_id=?";
    private static final String SELECT_BY_COURSE = "SELECT student_id, group_id, first_name, last_name, course_id, course_name "
                                                 + "FROM students JOIN students_courses USING (student_id) JOIN courses USING (course_id) "
                                                 + "WHERE course_id=?";

    private DBConnectionBuilder connectionBuilder = new DBConnectionBuilder();

    Integer addStudent(String firstName, String lastName) {
        int studentId = -1;

        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ONE,
                        Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.executeUpdate();

            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                studentId = key.getInt("student_id");
            }
            key.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentId;
    }

    List<Student> findAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet studentsData = statement.executeQuery()) {

            while (studentsData.next()) {
                students.add(fillStudentEntity(studentsData));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    Student getStudentById(int studentId) {
        Student student = null;
        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, studentId);
            ResultSet studentData = statement.executeQuery();

            if (studentData.next()) {
                student = fillStudentEntity(studentData);
            }
            studentData.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    void deleteStudentById(int studentId) {
        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, studentId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void assignStudentsToGroups(List<Integer> studentIds, List<Integer> groupIds) {
        Random random = new Random();

        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_ALL)) {
            connection.setAutoCommit(false);

            for (int id : studentIds) {
                statement.setInt(1, groupIds.get(random.nextInt(groupIds.size())));
                statement.setInt(2, id);
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    List<Student> findStudentsByCourseId(int courseId) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_COURSE)) {

            statement.setInt(1, courseId);
            ResultSet studentsData = statement.executeQuery();

            while (studentsData.next()) {
                students.add(fillStudentEntity(studentsData));
            }
            studentsData.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    private Student fillStudentEntity(ResultSet data) throws SQLException {
        Student student = new Student();
        student.setStudentId(data.getInt("student_id"));
        student.setFirstName(data.getString("first_name"));
        student.setLastName(data.getString("last_name"));
        student.setGroupId(data.getInt("group_id"));
        return student;
    }
}
