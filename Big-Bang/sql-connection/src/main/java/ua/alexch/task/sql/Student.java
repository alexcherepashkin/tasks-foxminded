package ua.alexch.task.sql;

public class Student {
    private int student_id;
    private int group_id;
    private String first_name;
    private String last_name;

    Student() {
    }

    Student(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    Student(int student_id, int group_id, String first_name, String last_name) {
        this.student_id = student_id;
        this.group_id = group_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    int getStudentId() {
        return student_id;
    }

    void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    int getGroupId() {
        return group_id;
    }

    void setGroupId(int group_id) {
        this.group_id = group_id;
    }

    String getFirstName() {
        return first_name;
    }

    void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    String getLastName() {
        return last_name;
    }

    void setLastName(String last_name) {
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
