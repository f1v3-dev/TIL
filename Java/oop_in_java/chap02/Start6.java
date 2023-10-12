package chap02;

public class Start6 extends Thread {
    static int share;

    public static void main(String[] args) {
        Start6 t1 = new Start6();
        Start6 t2 = new Start6();

        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(share++);

            try {
                sleep(2000);
            } catch (InterruptedException ignore) {
            }
        }
    }
}