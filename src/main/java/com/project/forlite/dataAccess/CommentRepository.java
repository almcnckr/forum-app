package com.project.forlite.dataAccess;

import com.project.forlite.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByUserIdAndPostId(Integer userId, Integer postId);

    List<Comment> findByUserId(Integer userId);

    List<Comment> findByPostId(Integer postId);
}
