package com.example.wowdungeontracker.domain;

// Need to update this Enum each season
public enum Dungeon {
    BRACKENHIDE_HOLLOW("Brackenhide Hollow"),
    HALLS_OF_INFUSION("Halls of Infusion"),
    NELTHARUS("Neltharus"),
    THE_UNDERROT("The Underrot"),
    FREEHOLD("Freehold"),
    NELTHARIONS_LAIR("Neltharion's Lair"),
    ULDAMAN_LEGACY_OF_TYR("Uldaman: Legacy of Tyr"),
    THE_VORTEX_PINNACLE("The Vortex Pinnacle");

    public final String dungeonName;

    Dungeon(String dungeonName) {
        this.dungeonName = dungeonName;
    }
}
