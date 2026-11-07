package com.adventure_mania.jproj.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adventure_mania.jproj.Entities.CharSkills;
import com.adventure_mania.jproj.Entities.SkillType;

public interface CharSkillsRepo extends JpaRepository<CharSkills, Long>{
    List<CharSkills> findAllByTypeSkill(SkillType skill);
}
