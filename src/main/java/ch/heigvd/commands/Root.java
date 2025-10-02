package ch.heigvd.commands;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(
        name = "program",
        mixinStandardHelpOptions = true, // Ajoute --help automatiquement
        description = "Applique une transformation à un fichier avec un pattern."
)
public class Root implements Callable<Integer> {

    @Option(
            names = {"-p", "--pattern"},
            description = "Pattern à utiliser pour la transformation (obligatoire).",
            required = true
    )
    private String pattern;

    @Parameters(
            index = "0",
            description = "Commande de transformation (ex: 'uppercase', 'filter')."
    )
    private String command;

    @Parameters(
            index = "1",
            description = "Fichier d'entrée."
    )
    private Path inputFile;

    @Parameters(
            index = "2",
            description = "Fichier de sortie."
    )
    private Path outputFile;

    @Override
    public Integer call() throws IOException {
        // Lire le fichier d'entrée
        String content = Files.readString(inputFile);

        // Appliquer la transformation (exemple : convertir en majuscules)
        String transformed = switch (command) {
            case "uppercase" -> content.toUpperCase();
            case "filter" -> content.replaceAll(pattern, "");
            default -> throw new IllegalArgumentException("Commande inconnue: " + command);
        };

        // Écrire le résultat
        Files.writeString(outputFile, transformed);
        System.out.println("Transformation terminée. Résultat dans: " + outputFile);
        return 0; // Code de sortie OK
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Root()).execute(args);
        System.exit(exitCode);
    }
}
