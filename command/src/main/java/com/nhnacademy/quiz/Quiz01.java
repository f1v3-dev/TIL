package com.nhnacademy.quiz;

public class Quiz01 {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < args.length; i++) {
                String cmd = args[i];
                if (args[i].contains("-t")) {
                    String nextCmd = args[i + 1];
                    if (!(nextCmd.equals("bash"))) {
                        throw new IllegalArgumentException(cmd + "의 옵션이 누락되었습니다.");
                    }
                    sb.append(cmd).append(" ").append(nextCmd).append("\n");
                    i++;
                    continue;
                }
                sb.append(cmd).append("\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println(sb);
    }
}
