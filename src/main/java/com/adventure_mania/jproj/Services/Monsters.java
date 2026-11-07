package com.adventure_mania.jproj.Services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.adventure_mania.jproj.Entities.MonsterEnt;
import com.adventure_mania.jproj.Repository.MonsterRepo;

@Service 
public class Monsters {
    private final MonsterRepo monsterRepo;

    public Monsters(MonsterRepo monsterRepo){
        this.monsterRepo = monsterRepo;
    }

    // Get a list of monsters
    public List<MonsterEnt> monsterList() {
        return monsterRepo.findAll();
    }
}
