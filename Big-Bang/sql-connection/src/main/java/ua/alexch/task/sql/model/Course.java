package ua.alexch.task.sql.model;

public class Course {
    private Long course_id;
    private String course_name;
    private String course_description;

    public Course() {
    }

    public Course(Long course_id, String course_name, String course_description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_description = course_description;
    }

    public Long getCourseId() {
        return course_id;
    }

    public void setCourseId(Long course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return course_name;
    }

    public void setName(String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return course_description;
    }

    public void setDescription(String course_description) {
        this.course_description = course_description;
    }

    @Override
    public String toString() {
        return "Course [" + "course_id=" + course_id
                        + ", course_name=" + course_name
                        + ", course_description="+ course_description + "]";
    }
}
