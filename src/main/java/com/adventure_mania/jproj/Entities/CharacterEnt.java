package com.adventure_mania.jproj.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity 
@Table (name = "character")
public class CharacterEnt {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer hp= 100;
    private Integer level= 1;
    private Integer max_exp= 1000;
    private Integer current_exp= 0;
    @Column (name = "class")
    private String CharClass = "Warrior";

    @ManyToMany 
    @JoinTable (
        name= "character_skills",
        joinColumns= @JoinColumn(name="character_id"),
        inverseJoinColumns=@JoinColumn(name="skills_id")
    )
    private List<CharSkills> skills = new ArrayList<>();


    @OneToOne (fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="account_id", nullable=false, unique=true)
    private UserEnt account;

    public UserEnt getAccount() {return account;}
    public void setAccount(UserEnt account) {this.account = account ;}
    
    public void addSkill(CharSkills skill) {
    if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        // Prevent adding the exact same skill twice
        if (!this.skills.contains(skill)) {
            this.skills.add(skill);
        }
    }

    public List<CharSkills> getSkills() {
        return skills;
    }
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public Integer getHp() {return hp;}
    public void setHp(Integer hp) {this.hp = hp;}
    public Integer getLevel() {return level;}
    public void setLevel(Integer level) {this.level = level;}
    public Integer getMax_exp() {return max_exp;}   
    public void setMax_exp(Integer max_exp) {this.max_exp = max_exp;}
    public Integer getCurrent_exp() {return current_exp;}
    public void setCurrent_exp(Integer current_exp) {this.current_exp = current_exp;}
    public String getCharClass() {return CharClass;}
    public void setCharClass(String CharClass) {this.CharClass = CharClass;}
    @Override
    public String toString() {
        return "CharacterEnt{" +
                "id=" + id +
                ", hp=" + hp +
                ", level=" + level +
                ", max_exp=" + max_exp +
                ", current_exp=" + current_exp +
                ", CharClass='" + CharClass + '\'' +
                ", accoun=" + account +
                '}';
            
    }
}
