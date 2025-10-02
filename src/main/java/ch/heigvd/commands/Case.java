package ch.heigvd.commands;

import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "case", description = "Set characters to uppercase or lowercase")
public class Case implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root parent;

    @Override
    public Integer call() throws Exception {
        System.out.println("Case Command Called in: " + parent.infile + " out: " + parent.outfile);
        return 0;
    }
}
