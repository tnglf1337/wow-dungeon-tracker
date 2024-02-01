package com.example.wowdungeontracker.service;

import com.example.wowdungeontracker.adapter.database.CharacterRepository;
import com.example.wowdungeontracker.domain.PlayerCharacter;

import java.util.List;

public class PlayerCharacterService {

    private final CharacterRepository characterRepository;

    public PlayerCharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<PlayerCharacter> findByUserName(String userName) {
        return characterRepository.findByUserName(userName);
    }

    public void insert(PlayerCharacter c) {
        characterRepository.save(c);
    }

    public void deleteCharById(Long id, String charName) {
        characterRepository.deleteAllByCharName(charName);
        characterRepository.deleteByCharacterId(id);
    }

    public boolean userHasCharacters(String userName) {
        int a = characterRepository.countCharactersByUserName(userName);

        return a == 0 ? false : true;
    }
}
