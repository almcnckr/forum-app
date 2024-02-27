package com.project.forlite.business.abstracts;

import com.project.forlite.business.responses.GetUserResponse;
import com.project.forlite.entities.User;

import java.util.List;

public interface UserService {
    List<GetUserResponse> getAll();

    User createUser(User user);

    GetUserResponse getUserById(int id);

    User updateUser(User user);

    void deleteById(int id);
}
