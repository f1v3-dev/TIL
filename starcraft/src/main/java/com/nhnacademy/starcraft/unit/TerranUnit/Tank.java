package com.nhnacademy.starcraft.unit.TerranUnit;

import com.nhnacademy.starcraft.race.Terran.Terran;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

public class Tank extends GroundUnit {

    //Tank: 날 수 없는 유닛이며, 7의 공격력과 15의 방어력을 가집니다.
    public Tank(Terran terran) {
        super(terran, "Tank", 7, 15, new Weapon("Cannon"));
    }
}
