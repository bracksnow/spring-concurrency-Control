package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.User.request.UserCreateRequest;
import com.group.libraryapp.dto.User.request.UserUpdateRequest;
import com.group.libraryapp.dto.User.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.userRepository = new UserRepository(jdbcTemplate);
    }

    public void updateUser(UserUpdateRequest userUpdateRequest){
        if(userRepository.isUserNotExist(userUpdateRequest.getId())){
            throw new IllegalArgumentException();
        }
        userRepository.updateUserName(userUpdateRequest.getName(), userUpdateRequest.getId());
    }

    public void saveUser(UserCreateRequest userCreateRequest) {
        userRepository.saveUser(userCreateRequest.getName(), userCreateRequest.getAge());
    }
    public void deleteUser(String name){
        if(userRepository.isUserNotExistByName(name)){
            throw new IllegalArgumentException();
        }
        userRepository.deleteUser(name);
    }

    public List<UserResponse> getUser(){
        return userRepository.getUser();

    }
}
