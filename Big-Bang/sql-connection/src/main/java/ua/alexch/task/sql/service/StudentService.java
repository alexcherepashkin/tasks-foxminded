package ua.alexch.task.sql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ua.alexch.task.sql.DBConnectionFactory;
import ua.alexch.task.sql.model.Student;

public class StudentService {
    private static final String INSERT_ONE = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM students ORDER BY student_id";
    private static final String SELECT_BY_ID = "SELECT * FROM students WHERE student_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM students WHERE student_id=?";
    private static final String UPDATE_ALL = "UPDATE students SET group_id=? WHERE student_id=?";
    private static final String SELECT_BY_COURSE = "SELECT student_id, group_id, first_name, last_name, course_id, course_name "
                                                 + "FROM students JOIN students_courses USING (student_id) JOIN courses USING (course_id) "
                                                 + "WHERE course_id=?";

    private final DBConnectionFactory connectionFactory;
    
    public StudentService(DBConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Long addStudent(String firstName, String lastName) {
        Long studentId = -1L;

        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ONE,
                        Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.executeUpdate();

            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                studentId = key.getLong("student_id");
            }
            key.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentId;
    }

    public List<Student> findAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = connectionFactory.getConnection();
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

    public Student getStudentById(Long studentId) {
        Student student = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setLong(1, studentId);
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

    public void deleteStudentById(Long studentId) {
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setLong(1, studentId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignStudentsToGroups(List<Long> studentIds, List<Long> groupIds) {
        Random random = new Random();

        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_ALL)) {
            connection.setAutoCommit(false);

            for (Long id : studentIds) {
                statement.setLong(1, groupIds.get(random.nextInt(groupIds.size())));
                statement.setLong(2, id);
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> findStudentsByCourseId(Long courseId) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_COURSE)) {

            statement.setLong(1, courseId);
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
        student.setStudentId(data.getLong("student_id"));
        student.setFirstName(data.getString("first_name"));
        student.setLastName(data.getString("last_name"));
        student.setGroupId(data.getLong("group_id"));
        return student;
    }
}
