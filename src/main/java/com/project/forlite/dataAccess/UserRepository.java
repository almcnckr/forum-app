package com.project.forlite.dataAccess;

import com.project.forlite.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
