package ch.heigvd.commands;

import ch.heigvd.Transformation;
import picocli.CommandLine;

import java.util.concurrent.Callable;

class CaesarTransform extends Transformation {

    int shift;

    public CaesarTransform(int shift) {
        this.shift = shift;
    }

    @Override
    public String transform(String input) {
        String output = "";
        char[] chars = input.toCharArray();
        for(char c : chars){
            char baseCharacter = Character.isUpperCase(c) ? 'A' : 'a';
            int newAlphabetPosition = (c - baseCharacter + shift) % 26;
            char newCharacter = (char) (baseCharacter + newAlphabetPosition);
            output += newCharacter;
        }
        return output;
    }
}

@CommandLine.Command(name = "caesar", description = "Cipher message using caesar")
public class CaesarCommand implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root parent;

    @CommandLine.Option(
            names = {"-s", "--shift"},
            description = "defines the shift value",
            required = true
    )
    private int shift;

    @Override
    public Integer call() throws Exception {
        CaesarTransform transformation = new CaesarTransform(shift);
        return parent.transform_patterns(transformation);
    }
}
