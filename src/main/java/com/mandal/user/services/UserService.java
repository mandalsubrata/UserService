package com.mandal.user.services;

import com.mandal.user.entity.User;
import com.mandal.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public User getUser(Long id) {
        Optional<User> byId = userRepo.findById(id);
        return byId.orElseGet(this::dummyUser);
    }

    public List<User> getUsers() {
       return userRepo.findAll();
    }

    public User deleteUser(Long id) {
        Optional<User> byId = userRepo.findById(id);
        if(byId.isPresent()){
            userRepo.deleteById(id);
            return byId.get();
        }
        return null;
    }

    public User saveUser(User user) {
        // Logic to save user to database or any storage
        System.out.println("Saving user: " + user);
        User save = userRepo.save(user);
        System.out.println("post update Saving user: " + user);
        return save;
    }
    private User dummyUser() {
        return User.builder()
                .build();

    }
}
