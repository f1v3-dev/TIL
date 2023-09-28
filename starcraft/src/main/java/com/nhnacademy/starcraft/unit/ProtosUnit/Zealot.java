package com.nhnacademy.starcraft.unit.ProtosUnit;


import com.nhnacademy.starcraft.race.Protos.Protos;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Zealot: 날 수 없는 유닛이며, 5의 공격력과 20의 방어력을 가집니다.
public class Zealot extends GroundUnit {

    public Zealot(Protos protos) {
        super(protos, "Zealot", 5, 20, new Weapon("Psionic Blades"));
    }
}
