package ua.alexch.task.sql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.alexch.task.sql.DBConnectionFactory;
import ua.alexch.task.sql.model.Course;

public class CourseService {
    private static final String INSERT_ONE = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM courses ORDER BY course_id";
    private static final String SELECT_BY_ID = "SELECT * FROM courses WHERE course_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM courses WHERE course_id=?";
    private static final String INSERT_BY_IDS = "INSERT INTO students_courses (student_id, course_id) VALUES (?, ?)";
    private static final String REMOVE_BY_IDS = "DELETE FROM students_courses WHERE student_id=? AND course_id=?;";

    private final DBConnectionFactory connectionFactory;
    
    public CourseService(DBConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Long addCourse(String courseName, String courseDescription) {
        Long courseId = -1L;

        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ONE,
                        Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, courseName);
            statement.setString(2, courseDescription);
            statement.executeUpdate();

            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                courseId = key.getLong("course_id");
            }
            key.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseId;
    }

    public List<Course> findAllCourses() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet coursesData = statement.executeQuery()) {

            while (coursesData.next()) {
                courses.add(fillCourseEntity(coursesData));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public Course getCourseById(Long courseId) {
        Course course = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setLong(1, courseId);
            ResultSet courseData = statement.executeQuery();
            if (courseData.next()) {
                course = fillCourseEntity(courseData);
            }
            courseData.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    public void deleteCourseById(Long courseId) {
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setLong(1, courseId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enrollStudentToCourse(Long studentId, Long courseId) {
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_BY_IDS)) {

            statement.setLong(1, studentId);
            statement.setLong(2, courseId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeStudentFromCourse(Long studentId, Long courseId) {
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(REMOVE_BY_IDS)) {

            statement.setLong(1, studentId);
            statement.setLong(2, courseId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Course fillCourseEntity(ResultSet data) throws SQLException {
        Course course = new Course();
        course.setCourseId(data.getLong("course_id"));
        course.setName(data.getString("course_name"));
        course.setDescription(data.getString("course_description"));
        return course;
    }
}
