package com.nhnacademy.starcraft.unit.ProtosUnit;

import com.nhnacademy.starcraft.race.Protos.Protos;
import com.nhnacademy.starcraft.race.Race;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.SpecialWeapon;
import com.nhnacademy.starcraft.weapon.Weapon;

//Dragoon: 날 수 없는 유닛이며, 3의 공격력과 15의 방어력을 가집니다. 레이저를 발사할 수 있습니다.
public class Dragoon extends GroundUnit {
    public Dragoon(Protos protos) {
        super(protos, "Dragoon", 3, 15, new SpecialWeapon("Laser"));
    }
}
