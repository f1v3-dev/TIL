package com.nhnacademy.starcraft.unit;

import com.nhnacademy.starcraft.race.Race;
import com.nhnacademy.starcraft.weapon.SpecialWeapon;
import com.nhnacademy.starcraft.weapon.Weapon;


public class GroundUnit extends Unit {

    public GroundUnit(Race race, String name, int attackPower, int defensePower, Weapon weapon) {
        super(race, name, attackPower, defensePower, weapon);
    }

    // 지상 유닛은 공중 유닛을 공격할 수 없습니다.
    // 하지만 Weapon이 미사일, 레이저 포 또는 침(Special Weapon)을 가진 유닛은 날 수 있는 유닛을 공격할 수 있습니다.
    @Override
    public void attack(Unit unit) {

        // 공격 대상이 공중 유닛인 경우
        if (unit.flyable()) {
            if (this.getWeapon() instanceof SpecialWeapon) {
                unit.defense(this);
            } else {
                throw new IllegalArgumentException("지상 유닛은 공중 유닛을 공격할 수 없습니다.");
            }
        } else {
            // 공격 대상이 지상 유닛인 경우
            unit.defense(this);
        }
    }
}
