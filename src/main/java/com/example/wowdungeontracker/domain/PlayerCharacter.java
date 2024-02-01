package com.example.wowdungeontracker.domain;

import org.springframework.data.annotation.Id;

public record PlayerCharacter(@Id Long characterId,
                              String userName,
                              String charName) {
}
