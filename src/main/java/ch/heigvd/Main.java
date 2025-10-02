package ch.heigvd;

import ch.heigvd.commands.Root;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Root root = new Root();

        int exitCode =
                new CommandLine(root)
                        .execute(args);

        System.exit(exitCode);

    }
}
