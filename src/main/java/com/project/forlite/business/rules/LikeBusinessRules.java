package com.project.forlite.business.rules;

import com.project.forlite.business.abstracts.LikeService;
import com.project.forlite.core.utilities.exceptions.BusinessException;
import com.project.forlite.dataAccess.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeBusinessRules {
    private LikeRepository likeRepository;

    public void checkIfLikeNotExists(int id){
        if (!likeRepository.existsById(id))
            throw new BusinessException("Like does not exits.");
    }
}
