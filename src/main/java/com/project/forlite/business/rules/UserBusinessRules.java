package com.project.forlite.business.rules;

import com.project.forlite.core.utilities.exceptions.BusinessException;
import com.project.forlite.dataAccess.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private UserRepository userRepository;

    public void checkIfUserNotExists(int id){
        if (!userRepository.existsById(id))
            throw new BusinessException("User does not exists.");
    }
}
