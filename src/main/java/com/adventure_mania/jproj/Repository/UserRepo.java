package com.adventure_mania.jproj.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adventure_mania.jproj.Entities.UserEnt;

public interface UserRepo extends JpaRepository<UserEnt, Long> {
    Optional<UserEnt> findByUsername(String username);
}
