package ch.heigvd.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Callable;

import ch.heigvd.Transformation;
import picocli.CommandLine;
import java.util.ArrayList;
import java.util.List;

/**
 * Rearrange the letters in alphabetical order
 * The usefulness of such a functionality is left to
 * the user's judgement.
 */
class Orderer extends Transformation {
    @Override
    public String transform(String input) {

        List<Character> chars = new ArrayList<>();
        for (char c : input.toCharArray()) {
            chars.add(c);
        }

        chars.sort(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Character.compare(Character.toLowerCase(c1), Character.toLowerCase(c2));
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Character c : chars) {
            sb.append(c);
        }

        return sb.toString();
    }
}

@CommandLine.Command(name = "order", description = "Rearrange characters in alphabetical order (very useful, trust me)")
public class Order implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root parent;

    @Override
    public Integer call() throws Exception {
        Transformation transformation =  new Orderer();
        return parent.transform_patterns(transformation);
    }
}
