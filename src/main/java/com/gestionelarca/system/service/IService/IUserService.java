package com.gestionelarca.system.service.IService;
import java.util.List;

import com.gestionelarca.system.model.User;

public interface IUserService {
    List<User> listUsers();
    
    User getUser(Long id);

    User register(User user);

    boolean login(String username, String password);

    void deleteUser(User user);
}
