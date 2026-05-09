package com.app.ecom.user;

//import org.apache.catalina.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        List<User> userList = userService.fetchAllUsers();
        return userList;
    }

    @PostMapping
    public void create(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        User user = userService.fetchById(id);

        return user;
    }
}
