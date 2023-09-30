package com.nhnacademy.starcraft.race.zerg;

import com.nhnacademy.starcraft.race.Race;
import com.nhnacademy.starcraft.unit.zerg.Guardian;
import com.nhnacademy.starcraft.unit.zerg.Hydralisk;
import com.nhnacademy.starcraft.unit.zerg.Mutalisk;
import com.nhnacademy.starcraft.unit.zerg.Queen;
import com.nhnacademy.starcraft.unit.zerg.Ultralisk;
import com.nhnacademy.starcraft.unit.zerg.Zergling;
import java.util.Random;

/**
 * Zerg 종족: 2개의 날 수 있는 유닛과 2개의 날 수 없는 유닛을 가집니다.
 * Zergling: 날 수 없는 유닛이며, 2의 공격력과 2의 방어력을 가집니다.
 * Hydralisk: 날 수 없는 유닛이며,3의 공격력과 7의 방어력을 가집니다. 침을 발사할 수 있습니다. (special)
 * Ultralisk: 날 수 없는 유닛이며, 5의 공격력과 15의 방어력을 가집니다.
 * Mutalisk: 날 수 있는 유닛이며, 2의 공격력과 8의 방어력을 가집니다.
 * Guardian: 날 수 있는 유닛이며, 3의 공격력과 6의 방어력을 가집니다.
 *
 * Queen: 날 수 있는 유닛, 공격력 15, 방어력 25
 */


public class Zerg extends Race {

    private static final int UNIT_COUNT = 8;

    // TODO : Zerg를 선택하면 8개의 유닛이 무작위로 생성됩니다.
    @Override
    public void generateUnits() {

        Random random = new Random();
        for (int i = 0; i < UNIT_COUNT; i++) {
            int randomNumber = random.nextInt(6);

            switch (randomNumber) {
                case 0:
                    unitList.add(new Zergling(this));
                    break;
                case 1:
                    unitList.add(new Hydralisk(this));
                    break;
                case 2:
                    unitList.add(new Ultralisk(this));
                    break;
                case 3:
                    unitList.add(new Mutalisk(this));
                    break;
                case 4:
                    unitList.add(new Guardian(this));
                    break;
                case 5:
                    unitList.add(new Queen(this));
                    break;
                default:
                    throw new IllegalArgumentException("잘못된 값이 입력되었습니다.");
            }
        }
    }
}
