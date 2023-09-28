package com.nhnacademy.starcraft.race.Terran;

import com.nhnacademy.starcraft.race.Race;
import com.nhnacademy.starcraft.unit.TerranUnit.Goliath;
import com.nhnacademy.starcraft.unit.TerranUnit.Marine;
import com.nhnacademy.starcraft.unit.TerranUnit.Tank;
import com.nhnacademy.starcraft.unit.TerranUnit.Valkyrie;
import com.nhnacademy.starcraft.unit.TerranUnit.Wraith;
import java.util.Random;


/**
 * Terran 종족: 2개의 날수 있는 유닛과 3개의 날 수 없는 유닛을 가집니다.
 * Marine: 날 수 없는 유닛이며, 3의 공격력과 10의 방어력을 가집니다.
 * Tank: 날 수 없는 유닛이며, 7의 공격력과 15의 방어력을 가집니다.
 * Goliath: 날 수 없는 유닛이며, 5의 공격력과 15의 방어력을 가집니다. 미사일을 발사 할 수 있습니다.
 * Wraith: 날 수 있는 유닛이며, 3의 공격력과 10의 방어력을 가집니다.
 * Valkyrie: 날 수 있는 유닛이며, 4의 공격력과 12의 방어력을 가집니다.
 */

public class Terran extends Race {

    private static final int UNIT_COUNT = 5;

    // TODO: Terran을 선택하면 5개의 유닛이 무작위로 생성됩니다.
    @Override
    public void generateUnits() {
        Random random = new Random();
        for (int i = 0; i < UNIT_COUNT; i++) {
            int randomNumber = random.nextInt(5);

            switch (randomNumber) {
                case 0:
                    unitList.add(new Marine(this));
                    break;
                case 1:
                    unitList.add(new Tank(this));
                    break;
                case 2:
                    unitList.add(new Valkyrie(this));
                    break;
                case 3:
                    unitList.add(new Goliath(this));
                    break;
                case 4:
                    unitList.add(new Wraith(this));
                    break;
            }
        }
    }


}