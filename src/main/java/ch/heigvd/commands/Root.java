package ch.heigvd.commands;

import ch.heigvd.Transformation;
import picocli.CommandLine;

import java.io.*;
import java.nio.charset.StandardCharsets;

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
            required = false,
            scope = CommandLine.ScopeType.INHERIT
    )
    private boolean repeat;

    @CommandLine.Parameters(index = "0", description = "output file", scope =  CommandLine.ScopeType.INHERIT)
    private String infile;

    @CommandLine.Parameters(index = "1", description = "input file", scope =  CommandLine.ScopeType.INHERIT)
    private String outfile;

    public Integer replace_patterns(Transformation t) {
        try (
                BufferedReader in = new BufferedReader(new FileReader(infile, StandardCharsets.UTF_8));
                BufferedWriter out = new BufferedWriter(new FileWriter(outfile, StandardCharsets.UTF_8));
        ) {
            int matched_index;
            String line;

            while ((line = in.readLine()) != null) {
                while ((matched_index = line.indexOf(pattern)) != -1) {
                    out.write(line.substring(0, matched_index));
                    out.write(t.transform(pattern));
                    line = line.substring(matched_index + pattern.length());
                }
                out.write(line);
                out.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return 1;
        }
        return 0;
    }
}
