package templateCallbackPattern;

import strategyPattern.Strategy;

public class Client {
    public static void main(String[] args) {
        Soldier rambo = new Soldier();

//        rambo.runContext(new Strategy() {
//            @Override
//            public void runStrategy() {
//                System.out.println("총! 총초종총 총! 총!");
//            }
//        });
//
//        System.out.println();
//
//        rambo.runContext(new Strategy() {
//            @Override
//            public void runStrategy() {
//                System.out.println("칼! 카가갈카 칼! 칼!");
//            }
//        });
//
//        System.out.println();
//
//        rambo.runContext(new Strategy() {
//            @Override
//            public void runStrategy() {
//                System.out.println("도끼! 독독..도도독 독ㄱ끼!");
//            }
//        });

        rambo.runContext("총! 총초종총 총! 총!");
        System.out.println();

        rambo.runContext("칼! 카가갈카 칼! 칼!");
        System.out.println();

        rambo.runContext("도끼! 독독..도도독 독ㄱ끼!");
    }
}
