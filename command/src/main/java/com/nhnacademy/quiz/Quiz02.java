package com.nhnacademy.quiz;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Quiz02 {

    /**
     * File을 추가하거나 삭제할 수 있는 프로그램을 만든다.
     * -a option은 파일 이름을 추가로 받아서 추가한다.
     * -r option은 파일 이름을 추가로 받아서 삭제한다.
     * -c option은 추가 인수 없이 모두 삭제한다.
     */
    public static void main(String[] args) {
        Options options = new Options();
        
        options.addOption("a", true, "파일 추가");
        options.addOption("r", true, "파일 삭제");
        options.addOption("c", false, "파일 모두 삭제");

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("a")) {
                System.out.println(cmd.getOptionValue("a") + " 이 추가 되었습니다.");
            }

            if (cmd.hasOption("r")) {
                System.out.println(cmd.getOptionValue("r") + " 이 삭제 되었습니다");
            }

            if (cmd.hasOption("c")) {
                System.out.println("모든 파일이 삭제되었습니다.");
            }

        } catch(ParseException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
