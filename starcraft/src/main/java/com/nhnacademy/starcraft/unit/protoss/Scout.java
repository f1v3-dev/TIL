package com.nhnacademy.starcraft.unit.protoss;

import com.nhnacademy.starcraft.race.protoss.Protoss;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Scout: 날 수 있는 유닛이며, 5의 공격력과 10의 방어력을 가집니다.
public class Scout extends AirUnit {

    public Scout(Protoss protoss) {
        super(protoss, "Scout", 5, 10, new Weapon("Photon Blasters"));
    }
}
