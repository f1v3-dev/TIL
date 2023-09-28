package com.nhnacademy.starcraft.unit.ZergUnit;

import com.nhnacademy.starcraft.race.Zerg.Zerg;
import com.nhnacademy.starcraft.unit.AirUnit;
import com.nhnacademy.starcraft.weapon.Weapon;

// Guardian: 날 수 있는 유닛이며, 3의 공격력과 6의 방어력을 가집니다.
public class Guardian extends AirUnit {

    public Guardian(Zerg zerg) {
        super(zerg, "Guardian", 3, 6, new Weapon("Acid Spores"));
    }

}
