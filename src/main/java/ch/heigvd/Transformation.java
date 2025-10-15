package ch.heigvd;

/**
 * Represents an abstract transformation on Strings
 */
public abstract class Transformation {
    /**
     * Applies the transformation
     * @param input String to be transformed
     * @return String after applying transformation
     */
    public abstract String transform(String input);
}
