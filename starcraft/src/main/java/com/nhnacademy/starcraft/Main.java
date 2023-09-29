package com.nhnacademy.starcraft;

import com.nhnacademy.starcraft.race.Race;
import com.nhnacademy.starcraft.race.protoss.Protoss;
import com.nhnacademy.starcraft.race.terran.Terran;
import com.nhnacademy.starcraft.race.zerg.Zerg;
import com.nhnacademy.starcraft.unit.Unit;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Race computerRace;
    private static Race playerRace;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("스타크래프트 게임을 시작합니다.");


        while (true) {
            try {
                System.out.println("플레이어의 종족을 선택하세요.");
                System.out.println("1. 프로토스");
                System.out.println("2. 테란");
                System.out.println("3. 저그");
                System.out.print("> ");

                int number = sc.nextInt();
                computerRace = selectRace((int) (Math.random() * 3) + 1);
                playerRace = selectRace(number);
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("숫자를 입력하세요.");
            } catch (IllegalArgumentException e) {
                sc.nextLine();
                System.out.println(e.getMessage());
            }
        }

        System.out.println("게임을 시작합니다.");
        playGame(computerRace, playerRace, sc);

    }

    private static void playGame(Race computerRace, Race playerRace, Scanner sc) {
        Random random = new Random();


        // TODO: 게임 진행
        // 턴이 시작되면, 공격을 실행할 유닛과 공격받을 적군 유닛을 선택하고 공격하면 적의 방어력이 감소됩니다.
        // 적군의 방어력이 0이하가 되면 적군의 유닛은 파괴됩니다.

        boolean playerTurn = true;
        System.out.println("=====================\n\n");
        while (computerRace.getUnitCount() != 0 || playerRace.getUnitCount() != 0) {

            if (computerRace.getUnitCount() == 0) {
                System.out.println("플레이어 승리!!");
                break;
            }

            if (playerRace.getUnitCount() == 0) {
                System.out.println("컴퓨터 승리 ㅠㅠ");
                break;
            }


            printUnits(computerRace);
            printUnits(playerRace);

            try {
                if (playerTurn) {
                    System.out.println("턴을 시작합니다.");
                    System.out.println("공격할 유닛의 번호와 공격받을 적군의 번호를 입력하세요.(ex. 1 2)");
                    System.out.println("");
                    System.out.print("> ");
                    int attackUnitNumber = sc.nextInt();
                    int defenseUnitNumber = sc.nextInt();
                    attackUnit(attackUnitNumber, defenseUnitNumber, playerRace, computerRace);
                } else {
                    System.out.println("컴퓨터의 턴입니다.");
                    int attackUnitNumber = random.nextInt(computerRace.getUnitCount()) + 1;
                    int defenseUnitNumber = random.nextInt(playerRace.getUnitCount()) + 1;
                    attackUnit(attackUnitNumber, defenseUnitNumber, computerRace, playerRace);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("숫자를 입력하세요.");
                continue;
            }

            playerTurn = !playerTurn;
            System.out.println();
        }

    }

    private static void attackUnit(int attackUnitNumber, int defenseUnitNumber, Race attackRace, Race defenseRace) {


        List<Unit> attackRaceUnits = attackRace.getUnitList();
        List<Unit> defenseRaceUnits = defenseRace.getUnitList();

        Unit attackUnit;
        Unit defenseUnit;
        try {
            attackUnit = attackRaceUnits.get(attackUnitNumber - 1);
            defenseUnit = defenseRaceUnits.get(defenseUnitNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("유닛 번호를 잘못 입력하셨습니다.");
        }

        try {
            System.out.println(attackUnit.getName() + "이(가) " + defenseUnit.getName() + "을(를) 공격합니다.");
            attackUnit.attack(defenseUnit);
            System.out.println(defenseUnit.getName() + "의 남은 방어력 : " + defenseUnit.getDefensePower());
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }


    private static void printUnits(Race race) {

        if (race == playerRace) {
            System.out.println("[아군 유닛 (" + race.getClass().getSimpleName() + ")]");
        } else {
            System.out.println("[적군 유닛 (" + race.getClass().getSimpleName() + ")]");
        }

        race.printUnits();
        System.out.println();
    }

    private static Race selectRace(int number) {
        switch (number) {
            case 1:
                return new Protoss();
            case 2:
                return new Terran();
            case 3:
                return new Zerg();
            default:
                throw new IllegalArgumentException("1 ~ 3 사이의 값을 입력하세요.");
        }
    }
}

