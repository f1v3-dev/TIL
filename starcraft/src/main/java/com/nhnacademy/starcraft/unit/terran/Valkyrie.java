package com.nhnacademy.starcraft.unit.terran;

import com.nhnacademy.starcraft.race.terran.Terran;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

public class Valkyrie extends AirUnit {
    // Valkyrie: 날 수 있는 유닛이며, 4의 공격력과 12의 방어력을 가집니다.
    public Valkyrie(Terran terran) {
        super(terran, "Valkyrie", 4, 12, new Weapon("Rocket"));
    }
}
