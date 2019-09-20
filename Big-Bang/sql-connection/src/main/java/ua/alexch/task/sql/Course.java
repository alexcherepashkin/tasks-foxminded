package ua.alexch.task.sql;

public class Course {
    private int course_id;
    private String course_name;
    private String course_description;

    Course() {
    }

    Course(int course_id, String course_name, String course_description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_description = course_description;
    }

    int getCourseId() {
        return course_id;
    }

    void setCourseId(int course_id) {
        this.course_id = course_id;
    }

    String getName() {
        return course_name;
    }

    void setName(String course_name) {
        this.course_name = course_name;
    }

    String getDescription() {
        return course_description;
    }

    void setDescription(String course_description) {
        this.course_description = course_description;
    }

    @Override
    public String toString() {
        return "Course [" + "course_id=" + course_id
                        + ", course_name=" + course_name
                        + ", course_description="+ course_description + "]";
    }
}
