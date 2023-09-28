package com.nhnacademy.starcraft.unit.TerranUnit;

import com.nhnacademy.starcraft.race.Terran.Terran;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

public class Wraith extends AirUnit {
    // Wraith: 날 수 있는 유닛이며, 3의 공격력과 10의 방어력을 가집니다.
    public Wraith(Terran terran) {
        super(terran, "Wraith", 3, 10, new Weapon("Laser"));
    }
}
