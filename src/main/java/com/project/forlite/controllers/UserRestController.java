package com.project.forlite.controllers;

import com.project.forlite.business.abstracts.UserService;
import com.project.forlite.business.responses.GetUserResponse;
import com.project.forlite.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private UserService userService;

    @GetMapping
    public List<GetUserResponse> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public GetUserResponse getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable int userId){
        userService.deleteById(userId);
    }
}
