package com.nhnacademy.starcraft.race.Protos;

import com.nhnacademy.starcraft.race.Race;
import com.nhnacademy.starcraft.unit.ProtosUnit.Corsair;
import com.nhnacademy.starcraft.unit.ProtosUnit.Dragoon;
import com.nhnacademy.starcraft.unit.ProtosUnit.HighTempler;
import com.nhnacademy.starcraft.unit.ProtosUnit.Scout;
import com.nhnacademy.starcraft.unit.ProtosUnit.Zealot;
import java.util.Random;

/**
 * Protos 종족: 2개의 날수 있는 유닛과 3개의 날 수 없는 유닛을 가집니다.
 * Zealot: 날 수 없는 유닛이며, 5의 공격력과 20의 방어력을 가집니다.
 * Dragoon: 날 수 없는 유닛이며, 3의 공격력과 15의 방어력을 가집니다. 레이저를 발사할 수 있습니다.
 * HighTempler: 날 수 없는 유닛이며, 10의 공격력과 2의 방어력을 가집니다.
 * Scout: 날 수 있는 유닛이며, 5의 공격력과 10의 방어력을 가집니다.
 * Corsair: 날 수 있는 유닛이며, 4의 공격력과 12의 방어력을 가집니다.
 */

public class Protos extends Race {

    private static final int UNIT_COUNT = 4;

    // TODO: Protos를 선택하면 4개의 유닛이 무작위로 생성됩니다.
    @Override
    public void generateUnits() {

        Random random = new Random();

        for (int i = 0; i < UNIT_COUNT; i++) {
            int randomNumber = random.nextInt(5);

            switch (randomNumber) {
                case 0:
                    unitList.add(new Zealot(this));
                    break;
                case 1:
                    unitList.add(new Dragoon(this));
                    break;
                case 2:
                    unitList.add(new HighTempler(this));
                    break;
                case 3:
                    unitList.add(new Scout(this));
                    break;
                case 4:
                    unitList.add(new Corsair(this));
                    break;
            }

        }

    }
}
