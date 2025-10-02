package ch.heigvd.commands;

import ch.heigvd.Transformation;
import picocli.CommandLine;

import java.util.concurrent.Callable;

class ReplaceBy extends Transformation {

    private final String text;

    ReplaceBy(String text) {
        this.text = text;
    }

    @Override
    public String transform(String input) {
        return text;
    }
}

@CommandLine.Command(name = "replace", description = "Replaces pattern by substitute text")
public class Replace implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root parent;

    @CommandLine.Option(
            names = { "-s", "--substitute" },
            description = "text to put instead of pattern",
            required = true
    )
    private String substitute_text;

    @Override
    public Integer call() throws Exception {
        Transformation transformation = new ReplaceBy(substitute_text);
        return parent.transform_patterns(transformation);
    }
}
