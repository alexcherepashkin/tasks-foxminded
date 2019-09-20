package ua.alexch.task.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private static final String INSERT_ONE = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM courses ORDER BY course_id";
    private static final String SELECT_BY_ID = "SELECT * FROM courses WHERE course_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM courses WHERE course_id=?";
    private static final String INSERT_BY_IDS = "INSERT INTO students_courses (student_id, course_id) VALUES (?, ?)";
    private static final String REMOVE_BY_IDS = "DELETE FROM students_courses WHERE student_id=? AND course_id=?;";

    private DBConnectionBuilder connectionBuilder = new DBConnectionBuilder();

    Integer addCourse(String courseName, String courseDescription) {
        int courseId = -1;

        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ONE,
                        Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, courseName);
            statement.setString(2, courseDescription);
            statement.executeUpdate();

            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                courseId = key.getInt("course_id");
            }
            key.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseId;
    }

    List<Course> findAllCourses() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = connectionBuilder.getConnection();
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

    Course getCourseById(int courseId) {
        Course course = null;
        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, courseId);
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

    void deleteCourseById(int courseId) {
        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, courseId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void enrollStudentToCourse(int studentId, int courseId) {
        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_BY_IDS)) {

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void removeStudentFromCourse(int studentId, int courseId) {
        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(REMOVE_BY_IDS)) {

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Course fillCourseEntity(ResultSet data) throws SQLException {
        Course course = new Course();
        course.setCourseId(data.getInt("course_id"));
        course.setName(data.getString("course_name"));
        course.setDescription(data.getString("course_description"));
        return course;
    }
}
