package com.adventure_mania.jproj.Records;

public record CharacterDetails(Integer id,
    Integer hp,
    Integer level,
    Integer currentExp,
    Integer maxExp,
    String characterClass
) {
    
}
