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
    private static final int PROTOSS = 1;
    private static final int TERRAN = 2;
    private static final int ZERG = 3;

    private static Race playerRace;
    private static Race computerRace;

    private static Random random = new Random();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("스타크래프트 게임을 시작합니다.");

        // 플레이어와 컴퓨터의 종족을 선택합니다. (플레이어는 선택, 컴퓨터는 랜덤)
        selectPlayerRace(sc);
        computerRace = selectRace(random.nextInt(3) + 1);

        System.out.println("게임을 시작합니다.");
        playGame(computerRace, playerRace, sc);

    }

    private static void selectPlayerRace(Scanner sc) {
        while (true) {
            try {
                printRaceSelection();
                playerRace = selectRace(sc.nextInt());
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("1 ~ 3 사이의 숫자를 입력해주세요.");
            } catch (IllegalArgumentException e) {
                sc.nextLine();
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printRaceSelection() {
        System.out.println("플레이어의 종족을 선택하세요.");
        System.out.println(PROTOSS + ". 프로토스");
        System.out.println(TERRAN + ". 테란");
        System.out.println(ZERG + ". 저그");
        System.out.print("> ");
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

    private static void playGame(Race computerRace, Race playerRace, Scanner sc) {


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
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            playerTurn = !playerTurn;
            System.out.println();
        }

    }

    private static void attackUnit(int attackUnitNumber, int defenseUnitNumber, Race attackRace, Race defenseRace) {
        try {
            Unit attacker = getUnitFromList(attackUnitNumber, attackRace.getUnitList());
            Unit defender = getUnitFromList(defenseUnitNumber, defenseRace.getUnitList());

            attackUnit(attacker, defender);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("유닛 번호를 잘못 입력하셨습니다.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static void attackUnit(Unit attacker, Unit defender) {
        System.out.println(attacker.getName() + "이(가) " + defender.getName() + "을(를) 공격합니다.\n");
        try {
            performAttack(attacker, defender);

            if (defender.getDefensePower() > 0) {
                System.out.println(defender.getName() + "의 남은 방어력 : " + defender.getDefensePower());
            }
            System.out.println();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    private static void performAttack(Unit attacker, Unit defender) {
        try {
            attacker.attack(defender);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static Unit getUnitFromList(int attackUnitNumber, List<Unit> unitList) {
        return unitList.get(attackUnitNumber - 1);
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


}

