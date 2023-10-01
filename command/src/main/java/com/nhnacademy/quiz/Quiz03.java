package com.nhnacademy.quiz;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


/**
 * Usage: ls [OPTION]... [FILE]...
 * List information about the FILEs (the current directory by default).
 * Sort entries alphabetically if none of -cftuSUX nor --sort.
 * <p>
 * -a, --all                  do not hide entries starting with .
 * -A, --almost-all           do not list implied . and ..
 * -b, --escape               print octal escapes for non-graphic characters
 * --block-size=SIZE      use SIZE-byte blocks
 * -B, --ignore-backups       do not list implied entries ending with ~
 * -c                         with -lt: sort by, and show, ctime (time of last
 * modification of file status information)
 * with -l: show ctime and sort by name
 * otherwise: sort by ctime
 * -C                         list entries by columns
 */

public class Quiz03 {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("a", "all", false, "do not hide entries starting with .");
        options.addOption("A", "almost-all", false, "do not list implied . and ..");
        options.addOption("b", "escape", false, "print octal escapes for non-graphic characters");
        options.addOption(null, "block-size", true, "use SIZE-byte blocks");
        options.addOption("B", "ignore-backups", false, "do not list implied entries ending with ~");
        options.addOption("c", null, false, "with -lt: sort by, and show, ctime (time of last\n" +
                "                            modification of file status information)\n" +
                "                            with -l: show ctime and sort by name\n" +
                "                            otherwise: sort by ctime");
        options.addOption("C", null, false, "list entries by columns");

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("a")) {
                System.out.println("-a");
            }

            if (cmd.hasOption("A")) {
                System.out.println("-A");
            }

            if (cmd.hasOption("b")) {
                System.out.println("-b");
            }

            if (cmd.hasOption("block-size")) {
                System.out.println("-SIZE : " + cmd.getOptionValue("block-size"));
            }

            if (cmd.hasOption("B")) {
                System.out.println("-B");
            }

            if (cmd.hasOption("c")) {
                System.out.println("-c");
            }

            if (cmd.hasOption("C")) {
                System.out.println("-C");
            }

        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ls [OPTION]... [FILE]... \n" +
                    "List information about the FILEs (the current directory by default).\n" +
                    "Sort entries alphabetically if none of -cftuSUX nor --sort.", options);
        }
    }
}
