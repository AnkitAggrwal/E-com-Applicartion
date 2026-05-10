package com.app.ecom.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Optional<User> fetchById(Long id){
        return userRepository.findById(id);
    }

    public boolean updateUser(Long id, User updatedUser){
        User user = userRepository.findById(updatedUser.getId()).orElse(null);
        if (user == null) {
            return false;
        }
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());

        return true;
    }
}
