package statitics;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class StatisticsHolder {

    public static StatisticsHolder create(int methodsNumber, int totalChars, int totalLines) {
        return create("", methodsNumber, totalChars, totalLines);
    }

    public static StatisticsHolder create(String ast, int methodsNumber, int totalChars, int totalLines) {
        return new AutoValue_StatisticsHolder(ast, methodsNumber, totalChars, totalLines);
    }

    /** Visual representation of AST in xml-format. */
    public abstract String getAst();

    /** Number of methods. */
    public abstract int getMethodsNumber();

    /** Number of non-whitespace characters in methods. Includes methods' declarations. */
    public abstract int getTotalCharacters();

    /** Number of lines in methods. Includes methods' declarations. */
    public abstract int getTotalLines();

    public StatisticsHolder withAst(String ast) {
        return create(ast, getMethodsNumber(), getTotalCharacters(), getTotalLines());
    }
}
