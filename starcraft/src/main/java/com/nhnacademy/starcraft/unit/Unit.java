package com.nhnacademy.starcraft.unit;

import com.nhnacademy.starcraft.race.Race;
import com.nhnacademy.starcraft.weapon.Weapon;

/**
 * 1. 날 수 있는 유닛은 날 수 있는 유닛과 날 수 없는 유닛 모두를 공격할 수 있습니다.
 * 2. 날 수 없는 유닛은 날 수 있는 유닛을 공격할 수 없습니다.
 * 3. 미사일, 레이저 포 또는 침을 가진 유닛은 날 수 있는 유닛을 공격할 수 있습니다.
 * 4. 각 유닛은 공격력과 방어력을 가집니다.
 * 5. 한 유닛은 한 유닛을 공격할 수 있고, 공격 받은 유닛은 공격한 유닛이 가진 공격력만큼 방어력이 감소됩니다.
 * 6. 방어력이 0이된 유닛은 소멸됩니다.
 */
public abstract class Unit {

    private Race race;
    private String name;
    private int attackPower;
    private int defensePower;
    private Weapon weapon;

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getName() {
        return name;
    }

    private void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    Unit(Race race, String name, int attackPower, int defensePower, Weapon weapon) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("유닛의 이름은 빈 문자열이 될 수 없습니다.");
        }

        if (attackPower < 0 || defensePower < 0) {
            throw new IllegalArgumentException("공격력과 방어력은 0보다 작을 수 없습니다.");
        }

        this.race = race;
        this.name = name;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.weapon = weapon;
    }

    public boolean flyable() {
        return false;
    }

    public abstract void attack(Unit unit);

    // TODO: 방어를 하는 경우
    // 2. 방어를 하는 경우
    // 2.1. 한 유닛은 한 유닛을 공격할 수 있고, 공격 받은 유닛은 공격한 유닛이 가진 공격력만큼 방어력이 감소됩니다.
    // 2.2. 방어력이 0이된 유닛은 소멸됩니다.

    // a.attack(b) -> b.defense(a)를 해야한다.
    public void defense(Unit unit) {
        this.setDefensePower(getDefensePower() - unit.getAttackPower());

        // 방어력이 0이 된 유닛은 소멸되는 메서드를 호출
        if (this.getDefensePower() <= 0) {
            killUnit(unit.getName(), this.getName());
        }
    }


    private void killUnit(String attackerName, String defenderName) {
        this.race.removeDeadUnit(this);
        System.out.println(attackerName + "이(가) " + defenderName + "을(를) 죽였습니다!! \n");
    }

    @Override
    public String toString() {
        return this.getName() + " [공격력: " + this.getAttackPower() + "] " + " (현재 방어력: " + this.getDefensePower() + ")";
    }
}
