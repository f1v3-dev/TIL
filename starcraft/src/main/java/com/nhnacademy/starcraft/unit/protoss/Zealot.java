package com.nhnacademy.starcraft.unit.protoss;


import com.nhnacademy.starcraft.race.protoss.Protoss;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Zealot: 날 수 없는 유닛이며, 5의 공격력과 20의 방어력을 가집니다.
public class Zealot extends GroundUnit {

    public Zealot(Protoss protoss) {
        super(protoss, "Zealot", 5, 20, new Weapon("Psionic Blades"));
    }
}
