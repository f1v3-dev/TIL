package com.nhnacademy.starcraft.unit.ProtosUnit;

import com.nhnacademy.starcraft.race.Protos.Protos;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;


// Corsair: 날 수 있는 유닛이며, 4의 공격력과 12의 방어력을 가집니다.
public class Corsair extends AirUnit {
    public Corsair(Protos protos) {
        super(protos, "Corsair", 4, 12, new Weapon("Neutron Flare"));
    }
}
