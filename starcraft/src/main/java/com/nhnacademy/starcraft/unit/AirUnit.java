package com.nhnacademy.starcraft.unit;

import com.nhnacademy.starcraft.race.Race;
import com.nhnacademy.starcraft.weapon.Weapon;

/**
 * 1. 날 수 있는 유닛은 날 수 있는 유닛과 날 수 없는 유닛 모두를 공격할 수 있습니다.
 */
public class AirUnit extends Unit {
    public AirUnit(Race race, String name, int attackPower, int defensePower, Weapon weapon) {
        super(race, name, attackPower, defensePower, weapon);
    }

    @Override
    public boolean flyable() {
        return true;
    }

    // TODO: 공격을 하는 경우
    // 1. 공격을 하는 경우
    // 1.1. 날 수 있는 유닛은 날 수 있는 유닛과 날 수 없는 유닛 모두를 공격할 수 있습니다.
    // 1.2. 날 수 없는 유닛은 날 수 있는 유닛을 공격할 수 없습니다.
    // 1.3. 미사일, 레이저 포 또는 침을 가진 유닛은 날 수 있는 유닛을 공격할 수 있습니다.
    @Override
    public void attack(Unit unit) {
        // a.attack(b) -> b.defense(a)를 해야한다.
        unit.defense(this);
    }

}
