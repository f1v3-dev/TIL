
public class Main {
    public static void main(String[] args) {
        String name = new String("seungjo");
        System.out.println(name.substring(0, 1).concat(name.substring(6, 7)));
        System.out.println(name.substring(6, 7).concat(name.substring(0, 1)));

        StringBuffer sb  = new StringBuffer(name);
        System.out.println(sb.toString());
        sb.replace(0, 1, sb.substring(0, 1).toUpperCase());
        sb.replace(6, 7, sb.substring(6, 7).toUpperCase());
        System.out.println(sb.toString());
    }
}