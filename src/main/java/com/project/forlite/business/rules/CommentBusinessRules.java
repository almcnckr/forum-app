package com.project.forlite.business.rules;

import com.project.forlite.core.utilities.exceptions.BusinessException;
import com.project.forlite.dataAccess.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentBusinessRules {
    private CommentRepository commentRepository;

    public void checkIfCommentNotExists(int id){
        if (!commentRepository.existsById(id))
            throw new BusinessException("Comment does not exits.");
    }
}
