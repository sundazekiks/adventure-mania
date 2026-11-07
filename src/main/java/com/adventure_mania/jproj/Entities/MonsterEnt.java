package com.adventure_mania.jproj.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity 
@Table (name = "monsters")
public class MonsterEnt {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer hp;
    private Integer exp_drop;
    private Integer level;

    @ManyToMany
    @JoinTable (
        name = "monster_with_skills",
        joinColumns = @JoinColumn(name = "monster_id"),
        inverseJoinColumns = @JoinColumn(name = "monster_skill_id")
    )
    private List<SkillEnt> skills = new ArrayList<>();

    @Override 
    public String toString(){
            return "UserEnt{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp='" + hp + '\'' +
                ", exp_drop='" + exp_drop + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public void addSkill(SkillEnt skill) {
        this.skills.add(skill);
    };

    // Getters and Setters
    public Long getId() {return id;}
    public String getName() { return name;}
    public Integer getHp() { return hp;}
    public Integer getExpDrop() { return exp_drop;}
    public Integer getLevel() { return level;}

    public List<SkillEnt> getSkills() { return skills;}
       
}
