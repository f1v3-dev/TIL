package com.nhnacademy.starcraft.unit.zerg;

import com.nhnacademy.starcraft.race.zerg.Zerg;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.SpecialWeapon;

// Hydralisk: 날 수 없는 유닛이며,3의 공격력과 7의 방어력을 가집니다. 침을 발사할 수 있습니다.
public class Hydralisk extends GroundUnit {

    public Hydralisk(Zerg zerg) {
        super(zerg, "Hydralisk", 3, 7, new SpecialWeapon("Needle"));
    }
}
