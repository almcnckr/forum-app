package com.project.forlite.business.rules;

import com.project.forlite.core.utilities.exceptions.BusinessException;
import com.project.forlite.dataAccess.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostBusinessRules {
    private PostRepository postRepository;

    public void checkIfPostNotExists(int id){
        if (!postRepository.existsById(id))
            throw new BusinessException("Post does not exits.");
    }
}
