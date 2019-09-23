package ua.alexch.task.sql;

import java.util.List;

import ua.alexch.task.sql.model.Course;
import ua.alexch.task.sql.model.Group;
import ua.alexch.task.sql.model.Student;
import ua.alexch.task.sql.service.CourseService;
import ua.alexch.task.sql.service.GroupService;
import ua.alexch.task.sql.service.StudentService;

public class UniversityFacade {
    private final GroupService groupService;
    private final CourseService courseService;
    private final StudentService studentService;

    public UniversityFacade(DBConnectionFactory connectionFactory) {
        this.groupService = new GroupService(connectionFactory);
        this.courseService = new CourseService(connectionFactory);
        this.studentService = new StudentService(connectionFactory);
    }

    public Long addGroup(String groupName) {
        return groupService.addGroup(groupName);
    }

    public List<Group> findAllGroups() {
        return groupService.findAllGroups();
    }

    public Group getGroupById(Long groupId) {
        return groupService.getGroupById(groupId);
    }

    public void deleteGroupById(Long groupId) {
        groupService.deleteGroupById(groupId);
    }

    public List<Group> findGroupsWithLessStudentCount() {
        return groupService.findGroupsWithLessStudentCount();
    }

    public Long addCourse(String courseName, String courseDescription) {
        return courseService.addCourse(courseName, courseDescription);
    }

    public List<Course> findAllCourses() {
        return courseService.findAllCourses();
    }

    public Course getCourseById(Long courseId) {
        return courseService.getCourseById(courseId);
    }

    public void deleteCourseById(Long courseId) {
        courseService.deleteCourseById(courseId);
    }

    public void enrollStudentToCourse(Long studentId, Long courseId) {
        courseService.enrollStudentToCourse(studentId, courseId);
    }

    public void removeStudentFromCourse(Long studentId, Long courseId) {
        courseService.removeStudentFromCourse(studentId, courseId);
    }

    public Long addStudent(String firstName, String lastName) {
        return studentService.addStudent(firstName, lastName);
    }

    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    public Student getStudentById(Long studentId) {
        return studentService.getStudentById(studentId);
    }

    public void deleteStudentById(Long studentId) {
        studentService.deleteStudentById(studentId);
    }

    public void assignStudentsToGroups(List<Long> studentIds, List<Long> groupIds) {
        studentService.assignStudentsToGroups(studentIds, groupIds);
    }

    public List<Student> findStudentsByCourseId(Long courseId) {
        return studentService.findStudentsByCourseId(courseId);
    }
}
