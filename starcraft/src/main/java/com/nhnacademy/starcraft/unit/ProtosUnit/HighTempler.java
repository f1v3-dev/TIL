package com.nhnacademy.starcraft.unit.ProtosUnit;

import com.nhnacademy.starcraft.race.Protos.Protos;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// HighTempler: 날 수 없는 유닛이며, 10의 공격력과 2의 방어력을 가집니다.
public class HighTempler extends GroundUnit {

    public HighTempler(Protos protos) {
        super(protos, "HighTempler", 10, 2, new Weapon("Psionic Storm"));
    }
}
