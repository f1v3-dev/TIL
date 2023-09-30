package com.nhnacademy.starcraft.unit.terran;

import com.nhnacademy.starcraft.race.terran.Terran;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// BattleCruzer: 날 수 있는 유닛, 공격력 20, 방어력.
public class Battlecruiser extends AirUnit {

    public Battlecruiser(Terran terran) {
        super(terran, "Battlecruiser", 20, 10, new Weapon("Laser"));
    }
}
