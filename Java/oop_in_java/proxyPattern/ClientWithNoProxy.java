package proxyPattern;

public class ClientWithNoProxy {
    public static void main(String[] args) {
        // 프록시 X
        Service service = new Service();
        System.out.println(service.runSomething());
    }
}
