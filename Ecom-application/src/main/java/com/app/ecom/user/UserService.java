package com.app.ecom.user;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    private Long id = 1L;

    public List<User> fetchAllUsers(){
        return userList;
    }

    public void addUser(User user){
        user.setId(id++);
        userList.add(user);
    }

    public User fetchById(Long id){
        User user = null;

        for(User u : userList){
            if(u.id.equals(id)){
                user = u;
            }
        }

        return user;
    }
}
