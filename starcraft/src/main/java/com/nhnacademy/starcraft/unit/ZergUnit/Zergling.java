package com.nhnacademy.starcraft.unit.ZergUnit;

import com.nhnacademy.starcraft.race.Zerg.Zerg;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Zergling: 날 수 없는 유닛이며, 2의 공격력과 2의 방어력을 가집니다.
public class Zergling extends GroundUnit {

    public Zergling(Zerg zerg) {
        super(zerg, "Zergling", 2, 2, new Weapon("Claws"));
    }
}
