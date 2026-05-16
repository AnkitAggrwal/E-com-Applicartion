package com.app.ecom.user;

import com.app.ecom.user.dto.UserResponse;
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

    public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Optional<UserResponse> fetchById(Long id){
        return userRepository.findById(id).map(this::mapToUserResponse);
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

    private UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        if(user.getAddress() != null) response.setAddressDto(user.getAddress().toAddressDto());
        return response;
    }
}
