package ua.alexch.task.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private static final String INSERT_ONE = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String SELECT_ALL = "SELECT * FROM groups ORDER BY group_id";
    private static final String SELECT_BY_ID = "SELECT * FROM groups WHERE group_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM groups WHERE group_id=?";
    private static final String SELECT_BY_COUNT = "SELECT group_id, group_name FROM groups "
                                                + "JOIN students USING (group_id) "
                                                + "GROUP BY group_id, group_name "
                                                + "ORDER BY COUNT (*) LIMIT 2";

    private DBConnectionBuilder connectionBuilder = new DBConnectionBuilder();

    Integer addGroup(String groupName) {
        int groupId = -1;

        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ONE,
                        Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, groupName);
            statement.executeUpdate();

            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                groupId = key.getInt("group_id");
            }
            key.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupId;
    }

    List<Group> findAllGroups() {
        List<Group> groups = new ArrayList<>();

        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet groupsData = statement.executeQuery()) {

            while (groupsData.next()) {
                groups.add(fillGroupEntity(groupsData));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }

    Group getGroupById(int groupId) {
        Group group = null;
        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, groupId);
            ResultSet groupData = statement.executeQuery();
            if (groupData.next()) {
                group = fillGroupEntity(groupData);
            }
            groupData.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return group;
    }

    void deleteGroupById(int groupId) {
        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, groupId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    List<Group> findGroupsWithLessStudentCount() {
        List<Group> groups = new ArrayList<>();

        try (Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_COUNT);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                groups.add(fillGroupEntity(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }

    private Group fillGroupEntity(ResultSet data) throws SQLException {
        Group group = new Group();
        group.setGroupId(data.getInt("group_id"));
        group.setName(data.getString("group_name"));
        return group;
    }
}
