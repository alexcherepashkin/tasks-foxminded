package ua.alexch.task.sql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.alexch.task.sql.DBConnectionFactory;
import ua.alexch.task.sql.model.Group;

public class GroupService {
    private static final String INSERT_ONE = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String SELECT_ALL = "SELECT * FROM groups ORDER BY group_id";
    private static final String SELECT_BY_ID = "SELECT * FROM groups WHERE group_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM groups WHERE group_id=?";
    private static final String SELECT_BY_COUNT = "SELECT group_id, group_name FROM groups "
                                                + "JOIN students USING (group_id) "
                                                + "GROUP BY group_id, group_name "
                                                + "ORDER BY COUNT (*) LIMIT 2";

    private final DBConnectionFactory connectionFactory;
    
    public GroupService(DBConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Long addGroup(String groupName) {
        Long groupId = -1L;

        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ONE,
                        Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, groupName);
            statement.executeUpdate();

            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                groupId = key.getLong("group_id");
            }
            key.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupId;
    }

    public List<Group> findAllGroups() {
        List<Group> groups = new ArrayList<>();

        try (Connection connection = connectionFactory.getConnection();
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

    public Group getGroupById(Long groupId) {
        Group group = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setLong(1, groupId);
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

    public void deleteGroupById(Long groupId) {
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setLong(1, groupId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> findGroupsWithLessStudentCount() {
        List<Group> groups = new ArrayList<>();

        try (Connection connection = connectionFactory.getConnection();
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
        group.setGroupId(data.getLong("group_id"));
        group.setName(data.getString("group_name"));
        return group;
    }
}
