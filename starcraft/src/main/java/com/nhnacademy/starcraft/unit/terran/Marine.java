package com.nhnacademy.starcraft.unit.terran;

import com.nhnacademy.starcraft.race.terran.Terran;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

public class Marine extends GroundUnit {

    // Marine: 날 수 없는 유닛이며, 3의 공격력과 10의 방어력을 가집니다.
    public Marine(Terran terran) {
        super(terran, "Marine", 3, 10, new Weapon("Rifle"));
    }
}
