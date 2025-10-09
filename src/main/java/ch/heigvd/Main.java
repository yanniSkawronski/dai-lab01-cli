package ch.heigvd;

import ch.heigvd.commands.Root;
import picocli.CommandLine;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Define command name - source: https://stackoverflow.com/a/11159435
        // (also java-ios dai project where this line is from)
        String jarFilename =
        new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath())
            .getName();

        Root root = new Root();

        int exitCode =
            new CommandLine(root)
                .setCommandName(jarFilename)
                .execute(args);

        System.exit(exitCode);

    }
}
