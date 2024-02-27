package com.project.forlite.dataAccess;

import com.project.forlite.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Optional<Integer> userId);
}
