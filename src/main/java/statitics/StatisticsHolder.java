package statitics;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class StatisticsHolder {

    /** Visual representation of AST in xml-format. */
    public abstract String getAst();

    /** Number of methods. */
    public abstract int getMethodsNumber();

    /** Number of non-whitespace characters in methods. Includes methods' declarations. */
    public abstract int getTotalCharacters();

    /** Number of lines in methods. Includes methods' declarations. */
    public abstract int getTotalLines();

    public abstract Builder toBuilder();

    public static StatisticsHolder create() {
        return new AutoValue_StatisticsHolder.Builder()
                .setAst("")
                .setMethodsNumber(0)
                .setTotalCharacters(0)
                .setTotalLines(0)
                .build();
    }

    public StatisticsHolder addAst(String ast) {
        return toBuilder().setAst(getAst() + "\n" + ast).build();
    }

    public StatisticsHolder addMethodsNumber(int methodsNumber) {
        return toBuilder().setMethodsNumber(getMethodsNumber() + methodsNumber).build();
    }

    public StatisticsHolder addMethodsCharacters(int characters) {
        return toBuilder().setTotalCharacters(getTotalCharacters() + characters).build();
    }

    public StatisticsHolder addMethodsLines(int lines) {
        return toBuilder().setTotalLines(getTotalLines() + lines).build();
    }

    @AutoValue.Builder
    static abstract class Builder {

        public abstract Builder setAst(String ast);

        public abstract Builder setMethodsNumber(int methodsNumber);

        public abstract Builder setTotalCharacters(int totalCharacters);

        public abstract Builder setTotalLines(int totalLines);

        public abstract StatisticsHolder build();
    }
}
