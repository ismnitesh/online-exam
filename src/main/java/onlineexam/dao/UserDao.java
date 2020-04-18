package onlineexam.dao;

import onlineexam.model.User;

public class UserDao {
    public static User getUserById(int id) {
        return User.builder().build();
    }

    public static boolean save(User user) {
        return true;
    }
}
