package com.nhnacademy.starcraft.unit.protoss;


import com.nhnacademy.starcraft.race.protoss.Protoss;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Carrier: 날 수 있는 유닛, 공격력: 25, 방어력 40
public class Carrier extends AirUnit {

    public Carrier(Protoss protoss) {
        super(protoss, "Carrier", 25, 40, new Weapon("Interceptor"));
    }
}
