package com.nhnacademy.starcraft.unit.TerranUnit;

import com.nhnacademy.starcraft.race.Terran.Terran;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.SpecialWeapon;

public class Goliath extends GroundUnit {

    // Goliath: 날 수 없는 유닛이며, 5의 공격력과 15의 방어력을 가집니다. 미사일을 발사 할 수 있습니다.
    public Goliath(Terran terran) {
        super(terran, "Goliath", 5, 15, new SpecialWeapon("Missile"));
    }
}
