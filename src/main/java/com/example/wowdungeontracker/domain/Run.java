package com.example.wowdungeontracker.domain;

import org.springframework.data.annotation.Id;

public record Run(@Id Long runId,
                  String characterName,
                  Integer key,
                  String dungeon,
                  String drop,
                  Integer gearscore,
                  Integer finished,
                  String cleared,
                  String date,
                  String userName) {
}
