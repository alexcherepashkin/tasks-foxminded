package ua.alexch.task.sql;

import java.util.List;

public class DBManager {
    private final GroupService groupService;
    private final CourseService courseService;
    private final StudentService studentService;

    public DBManager() {
        this.groupService = new GroupService();
        this.courseService = new CourseService();
        this.studentService = new StudentService();
    }

    public Integer addGroup(String groupName) {
        return groupService.addGroup(groupName);
    }

    public List<Group> findAllGroups() {
        return groupService.findAllGroups();
    }

    public Group getGroupById(int groupId) {
        return groupService.getGroupById(groupId);
    }

    public void deleteGroupById(int groupId) {
        groupService.deleteGroupById(groupId);
    }

    public List<Group> findGroupsWithLessStudentCount() {
        return groupService.findGroupsWithLessStudentCount();
    }

    public Integer addCourse(String courseName, String courseDescription) {
        return courseService.addCourse(courseName, courseDescription);
    }

    public List<Course> findAllCourses() {
        return courseService.findAllCourses();
    }

    public Course getCourseById(int courseId) {
        return courseService.getCourseById(courseId);
    }

    public void deleteCourseById(int courseId) {
        courseService.deleteCourseById(courseId);
    }

    public void enrollStudentToCourse(int studentId, int courseId) {
        courseService.enrollStudentToCourse(studentId, courseId);
    }

    public void removeStudentFromCourse(int studentId, int courseId) {
        courseService.removeStudentFromCourse(studentId, courseId);
    }

    public Integer addStudent(String firstName, String lastName) {
        return studentService.addStudent(firstName, lastName);
    }

    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    public Student getStudentById(int studentId) {
        return studentService.getStudentById(studentId);
    }

    public void deleteStudentById(int studentId) {
        studentService.deleteStudentById(studentId);
    }

    public void assignStudentsToGroups(List<Integer> studentIds, List<Integer> groupIds) {
        studentService.assignStudentsToGroups(studentIds, groupIds);
    }

    public List<Student> findStudentsByCourseId(int courseId) {
        return studentService.findStudentsByCourseId(courseId);
    }
}
