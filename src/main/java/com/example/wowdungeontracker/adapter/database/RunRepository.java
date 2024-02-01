package com.example.wowdungeontracker.adapter.database;

import com.example.wowdungeontracker.domain.Run;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunRepository extends CrudRepository<Run, Long> {

    List<Run> findAllByCharacterName(String charName);

    int countByCharacterName(String charName);

    @Modifying
    @Query("DELETE FROM run " +
            "WHERE run_id = :run_id")
    void deleteByRunId(@Param("run_id") Long run_id);

    @Query("SELECT dungeon " +
            "FROM run " +
            "WHERE character_name = :character_name " +
            "GROUP BY dungeon " +
            "ORDER BY COUNT(dungeon) " +
            "DESC LIMIT 1")
    String mostRunDungeon(@Param("character_name") String character_name);

    @Query("SELECT character_name " +
            "FROM run " +
            "WHERE user_name = :user_name " +
            "GROUP BY character_name " +
            "ORDER BY COUNT(character_name) " +
            "DESC LIMIT 1")
    String mostUsedChar(@Param("user_name") String user_name);

    @Query("SELECT COUNT(drop) " +
            "FROM run " +
            "WHERE drop = 'true'")
    int countByDrop();


    @Query("SELECT COUNT(cleared) " +
            "FROM run " +
            "WHERE cleared = 'false' AND character_name = :character_name")
    int countFailed(@Param("character_name") String character_name);
}
