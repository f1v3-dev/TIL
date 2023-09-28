package com.nhnacademy.starcraft.unit.protoss;

import com.nhnacademy.starcraft.race.protoss.Protoss;
import com.nhnacademy.starcraft.unit.GroundUnit;
import com.nhnacademy.starcraft.weapon.SpecialWeapon;

//Dragoon: 날 수 없는 유닛이며, 3의 공격력과 15의 방어력을 가집니다. 레이저를 발사할 수 있습니다.
public class Dragoon extends GroundUnit {
    public Dragoon(Protoss protoss) {
        super(protoss, "Dragoon", 3, 15, new SpecialWeapon("Laser"));
    }
}
