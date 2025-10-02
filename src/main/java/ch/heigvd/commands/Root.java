package ch.heigvd.commands;

import ch.heigvd.Transformation;
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
public class Root {

    @CommandLine.Option(
            names = {"-p", "--pattern"},
            description = "pattern to find in file",
            required = true,
            scope = CommandLine.ScopeType.INHERIT
    )
    private String pattern;

    @CommandLine.Option(
            names = {"-r", "--repeat"},
            description = "transform all occurrences",
            required = false
    )
    private boolean repeat;

    @CommandLine.Parameters(index = "0", description = "output file", scope =  CommandLine.ScopeType.INHERIT)
    private String infile;

    @CommandLine.Parameters(index = "1", description = "input file", scope =  CommandLine.ScopeType.INHERIT)
    private String outfile;

    public Integer replace_patterns(Transformation t) {
        // TODO
        return 0;
    }
}
