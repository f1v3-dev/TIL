package com.nhnacademy.starcraft.unit.protoss;

import com.nhnacademy.starcraft.race.protoss.Protoss;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;


// Corsair: 날 수 있는 유닛이며, 4의 공격력과 12의 방어력을 가집니다.
public class Corsair extends AirUnit {
    public Corsair(Protoss protoss) {
        super(protoss, "Corsair", 4, 12, new Weapon("Neutron Flare"));
    }
}
