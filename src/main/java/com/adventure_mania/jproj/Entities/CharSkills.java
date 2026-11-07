package com.adventure_mania.jproj.Entities;

import java.util.List;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity 
@Table (name="skills")
public class CharSkills {
    @Id 
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;


    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name="type_skill")
     @JdbcType(PostgreSQLEnumJdbcType.class)
    private SkillType typeSkill;
    private String description;

    @ManyToMany(mappedBy="skills")
    private List<CharacterEnt> characters;

    public String getName() {return name;}
    public SkillType getTypeSkill() {return typeSkill;}
    public String getDescription() {return description;}

    @Override 
    public String toString(){
        return "{ id: " + id + "name: " + name + "skill_type: " + typeSkill + "description" + description;
    }
}
