package ch.heigvd.commands;

import picocli.CommandLine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "transform",
        description = "Applique une transformation à un fichier avec un pattern.",
        version = "0.0.1",
        subcommands = {
          Case.class
        },
        scope = CommandLine.ScopeType.INHERIT,
        mixinStandardHelpOptions = true
)
public class Root implements Callable<Integer> {

    @CommandLine.Option(
            names = {"-p", "--pattern"},
            description = "Pattern à utiliser pour la transformation.",
            required = true
    )
    private String pattern;

    @Override
    public Integer call() throws Exception {
        System.out.println("Root Command Called with pattern: " + pattern);
        return 0;
    }
}
