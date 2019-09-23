package ua.alexch.task.sql;

public class Group {
    private int group_id;
    private String group_name;

    Group() {
    }

    Group(int group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }

    int getGroupId() {
        return group_id;
    }

    void setGroupId(int group_id) {
        this.group_id = group_id;
    }

    String getName() {
        return group_name;
    }

    void setName(String group_name) {
        this.group_name = group_name;
    }

    @Override
    public String toString() {
        return "Group [" + "group_id=" + group_id
                       + ", group_name=" + group_name + "]";
    }
}
