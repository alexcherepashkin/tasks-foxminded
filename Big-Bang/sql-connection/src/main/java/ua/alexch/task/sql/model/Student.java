package ua.alexch.task.sql.model;

public class Student {
    private Long student_id;
    private Long group_id;
    private String first_name;
    private String last_name;

    public Student() {
    }

    public Student(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Student(Long student_id, Long group_id, String first_name, String last_name) {
        this.student_id = student_id;
        this.group_id = group_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Long getStudentId() {
        return student_id;
    }

    public void setStudentId(Long student_id) {
        this.student_id = student_id;
    }

    public Long getGroupId() {
        return group_id;
    }

    public void setGroupId(Long group_id) {
        this.group_id = group_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Student [" + "student_id=" + student_id
                         + ", group_id=" + group_id
                         + ", first_name=" + first_name
                         + ", last_name=" + last_name + "]";
    }
}
