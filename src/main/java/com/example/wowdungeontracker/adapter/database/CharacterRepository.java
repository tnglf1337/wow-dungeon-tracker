package com.example.wowdungeontracker.adapter.database;

import com.example.wowdungeontracker.domain.PlayerCharacter;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CharacterRepository extends CrudRepository<PlayerCharacter, Long> {
    List<PlayerCharacter> findByUserName(String userName);

    @Query("SELECT COUNT(char_name) " +
            "FROM player_character " +
            "WHERE user_name = :user_name")
    int countCharactersByUserName(@Param("user_name") String user_name);

    @Modifying
    @Query("DELETE FROM player_character " +
            "WHERE character_id = :character_id")
    void deleteByCharacterId(@Param("character_id") Long character_id);

    @Modifying
    @Query("DELETE FROM run " +
            "WHERE character_name = :character_name")
    void deleteAllByCharName(@Param("character_name") String character_name);
}
