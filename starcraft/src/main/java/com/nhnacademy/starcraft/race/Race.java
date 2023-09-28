package com.nhnacademy.starcraft.race;

import com.nhnacademy.starcraft.unit.Unit;
import java.util.ArrayList;
import java.util.List;

public abstract class Race {

    protected List<Unit> unitList;

    public Race() {
        this.unitList = new ArrayList<>();
        generateUnits();
    }

    public abstract void generateUnits();

    // TODO: 유닛의 방어력이 0인 경우 유닛을 제거
    public void removeDeadUnit(Unit deadUnit) {
        this.unitList.remove(deadUnit);
    }

    public void printUnits() {

        for (int i = 0; i < unitList.size(); i++) {
            System.out.println((i + 1) + " : " + unitList.get(i));

        }
    }

    public int getUnitCount() {
        return this.unitList.size();
    }

    public List<Unit> getUnitList() {
        return this.unitList;
    }
}