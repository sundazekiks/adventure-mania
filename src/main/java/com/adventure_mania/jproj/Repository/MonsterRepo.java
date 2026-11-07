package com.adventure_mania.jproj.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adventure_mania.jproj.Entities.MonsterEnt;

public interface MonsterRepo extends JpaRepository<MonsterEnt, Long> {
    @Query ("select m from MonsterEnt m inner join fetch m.skills where m.id = :id")
    Optional<MonsterEnt> findWithSkills(@Param ("id") Long id );
};
