package com.example.wowdungeontracker.service;

import com.example.wowdungeontracker.adapter.database.RunRepository;
import com.example.wowdungeontracker.domain.Dungeon;
import com.example.wowdungeontracker.domain.Run;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RunService {

    private final RunRepository runRepository;

    public RunService(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    public List<Run> findRunsByCharacterName(String charName) {
        return runRepository.findAllByCharacterName(charName);
    }


    public boolean characterHasRunned(String charName) {
        List<Run> l = runRepository.findAllByCharacterName(charName);

        if(l.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String mostRunDungeon(String charName) {
        String d = runRepository.mostRunDungeon(charName);
        return d;
    }

    public String mostUsedChar(String userName) {
        String e = runRepository.mostUsedChar(userName);
        return e;
    }

    public double dropRunRatio(String charName) {
        int a = runRepository.countByCharacterName(charName);
        int b = runRepository.countByDrop();
        double c;

        if(a == 0) {
            c = 0;
        } else {
            c = (double) b/a;
        }

        BigDecimal bd = new BigDecimal(c).setScale(2, RoundingMode.HALF_UP);
        double newInput = 100 * bd.doubleValue();

        return newInput;
    }

    public double failedRunRatio(String charName) {
        int a = runRepository.countByCharacterName(charName);
        int b = runRepository.countFailed(charName);
        double c;

        if(a == 0) {
            c = 0;
        } else {
            c = (double) b/a;
        }

        BigDecimal bd = new BigDecimal(c).setScale(2, RoundingMode.HALF_UP);
        double newInput = 100 * bd.doubleValue();

        return newInput;
    }

    public static List<Dungeon> dungeonList() {
        List<Dungeon> l = new ArrayList<>();

        l.add(Dungeon.THE_VORTEX_PINNACLE);
        l.add(Dungeon.THE_UNDERROT);
        l.add(Dungeon.NELTHARUS);
        l.add(Dungeon.ULDAMAN_LEGACY_OF_TYR);
        l.add(Dungeon.FREEHOLD);
        l.add(Dungeon.HALLS_OF_INFUSION);
        l.add(Dungeon.NELTHARIONS_LAIR);
        l.add(Dungeon.BRACKENHIDE_HOLLOW);

        return l;
    }
}
