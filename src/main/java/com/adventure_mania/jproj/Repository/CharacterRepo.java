package com.adventure_mania.jproj.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adventure_mania.jproj.Entities.CharacterEnt;


public interface CharacterRepo extends JpaRepository<CharacterEnt, Long> {
    Optional<CharacterEnt> findById(Integer id);    

    @Query ("select c from CharacterEnt c left join fetch c.skills where c.id = :id")
    Optional<CharacterEnt> findWithSkills(@Param("id") Long id);
}
