package com.nhnacademy.starcraft.unit.zerg;

import com.nhnacademy.starcraft.race.zerg.Zerg;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Ultralisk: 날 수 없는 유닛이며, 5의 공격력과 15의 방어력을 가집니다.
public class Ultralisk extends GroundUnit {

    public Ultralisk(Zerg zerg) {
        super(zerg, "Ultralisk", 5, 15, new Weapon("Kaiser Blades"));
    }
}
