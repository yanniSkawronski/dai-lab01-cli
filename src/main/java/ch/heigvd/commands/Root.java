package ch.heigvd.commands;

import ch.heigvd.Transformation;
import picocli.CommandLine;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommandLine.Command(
        description = "Applique une transformation à un fichier avec un pattern.",
        version = "0.0.1",
        subcommands = {
            Case.class,
            Replace.class,
            Order.class,
            CaesarCommand.class
        },
        scope = CommandLine.ScopeType.INHERIT,
        mixinStandardHelpOptions = true
)
public class Root {

    @CommandLine.Option(
            names = {"-p", "--pattern"},
            description = "pattern to find in file",
            required = true
    )
    private String pattern;

    @CommandLine.Option(
            names = {"-r", "--repeat"},
            description = "transform all occurrences",
            required = false
    )
    private boolean repeat;

    @CommandLine.Option(
            names = {"-e", "--regex"},
            description = "match regex pattern instead, max 1 match group",
            required = false
    )
    private boolean regex;

    @CommandLine.Parameters(index = "0", description = "output file", scope =  CommandLine.ScopeType.INHERIT)
    private String infile;

    @CommandLine.Parameters(index = "1", description = "input file", scope =  CommandLine.ScopeType.INHERIT)
    private String outfile;

    public Integer transform_patterns(Transformation t) {
       if (repeat) {
           return transform_many(t);
       } else {
           return transform_single(t);
       }
    }

    private Integer transform_single(Transformation t) {
        try (
                BufferedReader in = new BufferedReader(new FileReader(infile, StandardCharsets.UTF_8));
                BufferedWriter out = new BufferedWriter(new FileWriter(outfile, StandardCharsets.UTF_8));
        ) {
            String line;

            while ((line = in.readLine()) != null) {
                Optional<Match> match = find_match(line);
                if (match.isPresent()) {
                    out.write(line.substring(0, match.get().start));
                    out.write(t.transform(line.substring(match.get().start, match.get().end)));
                    out.write(line.substring(match.get().end));
                    out.newLine();
                    break;
                }
                out.write(line);
                out.newLine();
            }
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return 1;
        }
        return 0;
    }

    private Integer transform_many(Transformation t) {
        try (
                BufferedReader in = new BufferedReader(new FileReader(infile, StandardCharsets.UTF_8));
                BufferedWriter out = new BufferedWriter(new FileWriter(outfile, StandardCharsets.UTF_8));
        ) {
            int matched_index;
            String line;

            while ((line = in.readLine()) != null) {
                Optional<Match> match = find_match(line);
                while (match.isPresent()) {
                    out.write(line.substring(0, match.get().start));
                    out.write(t.transform(line.substring(match.get().start, match.get().end)));
                    line = line.substring(match.get().end);
                    match = find_match(line);
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

    //bundles match start and end in one object
    private class Match {
        final public int start, end;

        public Match(int start, int length) {
            this.start = start;
            this.end = length;
        }
    }

    // return index and length of match if exists, using regex if enabled
    private Optional<Match> find_match(String s){
        if (regex) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(s);
            if (m.find()) {
                Match match;
                if (m.groupCount() > 0) {
                    match = new Match(m.start(0), m.end(0));
                } else {
                    match = new Match(m.start(), m.end());
                }
                return Optional.of(match);
            } else {
                return Optional.empty();
            }
        } else {
            int start = s.indexOf(pattern);
            return start == -1 ? Optional.empty() : Optional.of(new  Match(start, start + pattern.length()));
        }
    }
}
