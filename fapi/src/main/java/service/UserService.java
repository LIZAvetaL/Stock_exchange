package service;
import model.User;

import java.util.List;

public interface UserService {

    User findByName(String name);
    List<User> findAll();
    void save(User user);
}
