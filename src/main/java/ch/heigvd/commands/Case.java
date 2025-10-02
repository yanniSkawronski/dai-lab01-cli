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

@CommandLine.Command(name = "case", description = "Set characters to uppercase or lowercase")
public class Case implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root parent;

    @Override
    public Integer call() throws Exception {
        System.out.println("Case Command Called");
        return 0;
    }
}
