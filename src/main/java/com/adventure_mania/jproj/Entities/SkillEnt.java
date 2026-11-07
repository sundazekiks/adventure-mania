package com.adventure_mania.jproj.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity 
@Getter @Setter 
@Table (name = "monster_skills")
public class SkillEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skill_name;
    private Integer damage;

    @ManyToMany (mappedBy = "skills")
    private List<MonsterEnt> monsters;

    public Long getId() {return id;}
    public String getSkillName() {return  skill_name;}
    public Integer getDamage() {return damage;}
    
}
