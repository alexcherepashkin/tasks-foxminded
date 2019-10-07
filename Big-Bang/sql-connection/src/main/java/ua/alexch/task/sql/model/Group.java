package ua.alexch.task.sql.model;

public class Group {
    private Long group_id;
    private String group_name;

    public Group() {
    }

    public Group(Long group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }

    public Long getGroupId() {
        return group_id;
    }

    public void setGroupId(Long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return group_name;
    }

    public void setName(String group_name) {
        this.group_name = group_name;
    }

    @Override
    public String toString() {
        return "Group [" + "group_id=" + group_id
                       + ", group_name=" + group_name + "]";
    }
}
