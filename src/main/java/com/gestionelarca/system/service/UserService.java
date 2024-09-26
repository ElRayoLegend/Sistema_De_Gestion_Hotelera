package com.gestionelarca.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.model.HotelReport;
import com.gestionelarca.system.model.User;
import com.gestionelarca.system.repository.UserRepository;
import com.gestionelarca.system.service.IService.IUserService;
import com.gestionelarca.system.utils.BCryptSecurity;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptSecurity bcrypt;

    @Override
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User register(User user){
        if(user.getPassword() != null){
            user.setPassword(bcrypt.encodePassword(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public boolean login(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user == null || !bcrypt.checkPassword(password, user.getPassword())){
            return false;
        }
        return true;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
