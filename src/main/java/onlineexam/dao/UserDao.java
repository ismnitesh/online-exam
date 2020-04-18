package onlineexam.dao;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;
import onlineexam.model.User;
import org.hibernate.SessionFactory;

@Slf4j
public class UserDao extends AbstractDAO<User> {
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User getUserById(int id) {
        return get(id);
    }

    public boolean save(User user) {
        try {
            persist(user);
        } catch (Exception e) {
            log.error("Exception while saving user details: {}", e.getMessage());
            return false;
        }
        return true;
    }
}
