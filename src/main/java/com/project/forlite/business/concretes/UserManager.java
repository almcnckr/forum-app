package com.project.forlite.business.concretes;

import com.project.forlite.business.abstracts.UserService;
import com.project.forlite.business.responses.GetUserResponse;
import com.project.forlite.business.rules.UserBusinessRules;
import com.project.forlite.core.utilities.exceptions.BusinessException;
import com.project.forlite.core.utilities.mappers.ModelMapperService;
import com.project.forlite.dataAccess.UserRepository;
import com.project.forlite.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private UserRepository userRepository;
    private UserBusinessRules userBusinessRules;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapperService.forResponse().map(user, GetUserResponse.class)).toList();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public GetUserResponse getUserById(int id) {
        userBusinessRules.checkIfUserNotExists(id);
        User user = userRepository.findById(id).orElse(null);

        return modelMapperService.forResponse().map(user, GetUserResponse.class);
    }

    @Override
    public User updateUser(User user) {
        userBusinessRules.checkIfUserNotExists(user.getId());

        return userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userBusinessRules.checkIfUserNotExists(id);

        userRepository.deleteById(id);
    }
}
