package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserRegistry {

    private Map<Integer, User> userMap;
    private List<Integer> newUsers;
    private List<Integer> updatedUsers;
    private List<Integer> deletedUsers;

    public UserRegistry(Map<Integer, User> userMap) {
        this.userMap = userMap;
    }

    public UserRegistry() {
        userMap = new TreeMap<>();
        newUsers = new ArrayList<>();
        updatedUsers = new ArrayList<>();
        deletedUsers = new ArrayList<>();
    }

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

    public void addNewUser(User u) {
        if (checkUser(u.getUserId())) {
            userMap.put(u.getUserId(), u);
            newUsers.add(u.getUserId());
        }
    }

    public void addUpdatedUser(User u) {

        if (!checkUser(u.getUserId())) {
            userMap.remove(u.getUserId());
            userMap.put(u.getUserId(), u);
            updatedUsers.add(u.getUserId());
        }
    }

    public void addDeletedUser(int u) {
        if (!checkUser(u)) {
            userMap.remove(u);
            deletedUsers.add(u);
        }
    }

    public boolean checkUser(int u) {
        return userMap.get(u) == null;
    }

    public List<Integer> getDeletedUsers() {
        return deletedUsers;
    }

    public List<Integer> getUpdatedUsers() {
        return updatedUsers;
    }

    public List<Integer> getNewUsers() {
        return newUsers;
    }

    public User getUserById(int id) {
        return userMap.get(id);
    }

    public User getUserByUsername(String username) {
        for (User u : userMap.values()) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

}
