package ch.heigvd.commands;

import ch.heigvd.Transformation;
import picocli.CommandLine;

import java.util.concurrent.Callable;

class CaesarTransform extends Transformation {

    int shift;
    boolean decipher;

    public CaesarTransform(int shift, boolean decipher) {
        this.shift = shift;
        this.decipher = decipher;
    }

    @Override
    public String transform(String input) {
        String output = "";
        char[] chars = input.toCharArray();
        for(char c : chars){
            char baseCharacter = Character.isUpperCase(c) ? 'A' : 'a';
            int newAlphabetPosition =
                    decipher ?
                            (c - baseCharacter + 26 - shift) % 26:
                            (c - baseCharacter + shift) % 26;
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

    @CommandLine.Option(
            names = {"-d", "--decipher"},
            description = "Deciphers the text"
    )
    boolean decipher;

    @Override
    public Integer call() throws Exception {
        CaesarTransform transformation = new CaesarTransform(shift, decipher);
        return parent.transform_patterns(transformation);
    }
}
