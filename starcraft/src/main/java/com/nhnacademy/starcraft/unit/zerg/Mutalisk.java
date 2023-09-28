package com.nhnacademy.starcraft.unit.zerg;


import com.nhnacademy.starcraft.race.zerg.Zerg;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Mutalisk: 날 수 있는 유닛이며, 2의 공격력과 8의 방어력을 가집니다.
public class Mutalisk extends AirUnit {

    public Mutalisk(Zerg zerg) {
        super(zerg, "Mutalisk", 2, 8, new Weapon("Glave Wurm"));
    }
}
