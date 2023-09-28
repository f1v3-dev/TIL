package com.nhnacademy.starcraft.unit.zerg;

import com.nhnacademy.starcraft.race.zerg.Zerg;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Queen: 날 수 있는 유닛, 공격력 15, 방어력 25
public class Queen extends AirUnit {
    public Queen(Zerg zerg) {
        super(zerg, "Queen", 15, 25, new Weapon("Broodling"));
    }
}
