package ch.heigvd.commands;

import java.util.concurrent.Callable;

import ch.heigvd.Transformation;
import picocli.CommandLine;

class Uppercase extends Transformation {
    @Override
    public String transform(String input) {
        return input.toUpperCase();
    }
}

class Lowercase extends Transformation {
    @Override
    public String transform(String input) {
        return input.toLowerCase();
    }
}

@CommandLine.Command(name = "case", description = "Set characters to uppercase (default) or lowercase")
public class Case implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root parent;

    @CommandLine.Option(
            names = {"-l", "--lowercase"},
            description = "switches to lowercase",
            required = false
    )
    private boolean is_lowercase;

    @Override
    public Integer call() throws Exception {
        Transformation transformation = is_lowercase ? new Lowercase(): new Uppercase();
        return parent.replace_patterns(transformation);
    }
}
