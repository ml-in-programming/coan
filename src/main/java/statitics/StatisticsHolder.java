package statitics;

import java.util.*;

/**
 * To add a feature you should add a field with its name and add it to a list according to its type (e.g. create field
 * named SYMBOL with value "Symbol" and add it to INT_FEATURES).
 */
public class StatisticsHolder {
    public static final List<String> JAVA_KEYWORDS = new ArrayList<>(Arrays.asList(
            "abstract", "continue", "for", "new", "switch",
            "assert", "default", "goto", "package", "synchronized",
            "boolean", "do", "if", "private", "this",
            "break", "double", "implements", "protected", "throw",
            "byte", "else", "import", "public", "throws",
            "case", "enum", "instanceof", "return", "transient",
            "catch", "extends", "int", "short", "try",
            "char", "final", "interface", "static", "void",
            "class", "finally", "long", "strictfp", "volatile",
            "const", "float", "native", "super", "while"
    ));

    /**
     * Path to file.
     */
    public static final String PATH = "Path";
    /**
     * Project's name.
     */
    public static final String PROJECT = "Project";
    /**
     * Visual representation of AST in xml-format.
     */
    public static final String AST = "Ast";
    /**
     * Number of methods.
     */
    public static final String METHODS = "Method";
    /**
     * Number of non-whitespace characters in methods. Includes methods' declarations.
     */
    public static final String METHODS_CHARACTERS = "MethodsCharacters";
    public static final String AVG_METHODS_CHARACTERS = "AvgMethodsCharacters";
    /**
     * Number of lines in methods. Includes methods' declarations.
     */
    public static final String METHODS_LINES = "MethodsLines";
    public static final String AVG_METHODS_LINES = "AvgMethodsLines";
    /**
     * Number of parameters in methods.
     */
    public static final String METHODS_PARAMETERS = "MethodsParameters";
    public static final String AVG_METHODS_PARAMETERS = "AvgMethodsParameters";
    public static final String STD_DEV_METHODS_PARAMETERS = "StdDevMethodsParameters";
    /**
     * Number of fields.
     */
    public static final String FIELDS = "Fields";
    /**
     * Number of public fields.
     */
    public static final String PRIVATE_FIELDS = "PrivateFields";
    public static final String RATIO_PRIVATE_FIELDS = "RatioPrivateFields";
    /**
     * Number of public fields.
     */
    public static final String PUBLIC_FIELDS = "PublicFields";
    public static final String RATIO_PUBLIC_FIELDS = "RatioPublicFields";
    /**
     * Number of local variables.
     */
    public static final String LOCAL_VARIABLES = "LocalVariables";
    /**
     * Total length of all fields.
     */
    public static final String FIELDS_LENGTH = "FieldsLength";
    public static final String AVG_FIELDS_LENGTH = "AvgFieldsLength";
    /**
     * Total length of all local variables.
     */
    public static final String VARIABLES_LENGTH = "VariablesLength";
    public static final String AVG_VARIABLES_LENGTH = "AvgVariablesLength";
    /**
     * Number of classes.
     */
    public static final String CLASSES = "Classes";
    /**
     * Number of interfaces.
     */
    public static final String INTERFACES = "Interfaces";
    /**
     * Number of inner classes.
     */
    public static final String INNER_CLASSES = "InnerClasses";
    /**
     * Number of for cycles.
     */
    public static final String FORS = "For";
    public static final String RATIO_FORS = "RatioFor";
    /**
     * Number of while cycles.
     */
    public static final String WHILES = "While";
    public static final String RATIO_WHILES = "AvgWhile";
    /**
     * Number of if statements.
     */
    public static final String IFS = "If";
    public static final String RATIO_IFS = "AvgIf";
    /**
     * Number of else statements.
     */
    public static final String ELSES = "Else";
    public static final String RATIO_ELSES = "AvgElse";
    /**
     * Number of "else if" statements.
     */
    public static final String ELSE_IFS = "ElseIf";
    public static final String RATIO_ELSE_IFS = "AvgElseIf";
    /**
     * Number of ternary operators (? :).
     */
    public static final String TERNARY = "TernaryOperator";
    public static final String RATIO_TERNARY = "AvgTernaryOperator";
    /**
     * Number of string literals.
     */
    public static final String STRING_CONSTANTS = "StringConstant";
    public static final String RATIO_STRING_CONSTANTS = "AvgStringConstant";
    /**
     * Number of integer constants.
     */
    public static final String INT_CONSTANTS = "IntConstant";
    public static final String RATIO_INT_CONSTANTS = "AvgIntConstant";
    /**
     * Number of char constants.
     */
    public static final String CHAR_CONSTANTS = "CharConstant";
    public static final String RATIO_CHAR_CONSTANTS = "AvgCharConstant";
    /**
     * Number of lambda functions.
     */
    public static final String LAMBDAS = "Lambda";
    public static final String RATIO_LAMBDAS = "AvgLambda";
    /**
     * Number of break statements.
     */
    public static final String BREAKS = "Break";
    public static final String RATIO_BREAKS = "AvgBreak";
    /**
     * Number of continue statements.
     */
    public static final String CONTINUES = "Continue";
    public static final String RATIO_CONTINUES = "AvgContinue";
    /**
     * Number of nulls.
     */
    public static final String NULLS = "Null";
    public static final String RATIO_NULLS = "AvgNull";
    /**
     * Number of comments starting with //
     */
    public static final String LINE_COMMENTS = "LineComment";
    public static final String RATIO_LINE_COMMENTS = "AvgLineComment";
    /**
     * Number of comments starting with /*.
     */
    public static final String BLOCK_COMMENTS = "BlockComment";
    public static final String RATIO_BLOCK_COMMENTS = "AvgBlockComment";
    /**
     * Number of java-doc comments.
     */
    public static final String JAVA_DOC_COMMENTS = "JavaDocComment";
    public static final String RATIO_JAVA_DOC_COMMENTS = "AvgJavaDocComment";
    /**
     * Length of file in characters.
     */
    public static final String TOTAL_LENGTH = "TotalLength";
    /**
     * Length of file in lines.
     */
    public static final String LINES = "Line";
    public static final String AVG_LINE_LENGTH = "AvgLineLength";
    public static final String STD_DEV_LINE_LENGTH = "StdDevLineLength";
    /**
     * Number of \t symbols in file.
     */
    public static final String TABS = "Tab";
    public static final String RATIO_TABS = "RatioTab";
    /**
     * Number of spaces in file.
     */
    public static final String SPACES = "Space";
    public static final String RATIO_SPACES = "RatioSpace";
    /**
     * True if most of lines in the file start with tabs opposed to spaces .
     */
    public static final String TABS_LEAD_LINES = "TabsLeadLines";
    /**
     * Has 3 options: "new line", "space" and "other" depending on whether most of "{" are preceded by new line symbol,
     * space or something else .
     **/
    public static final String PUNCTUATION_BEFORE_BRACE = "PunctuationBeforeBrace";
    /**
     * Number of empty lines.
     */
    public static final String EMPTY_LINES = "EmptyLine";
    public static final String RATIO_EMPTY_LINES = "RatioEmptyLine";
    /**
     * Number of whitespace characters.
     */
    public static final String WHITESPACE_CHARS = "Whitespace";
    public static final String RATIO_WHITESPACE_CHARS = "RatioWhitespace";

    public enum ValueType {
        INT,
        STRING,
        RATIO_TOTAL,
        RATIO_LINES,
        RATIO_FIELDS,
        AVG_FIELDS,
        AVG_VARIABLES,
        AVG_METHODS,
        AVG_LINES,
        STD_DEV_METHODS, STD_DEV_LINES, NOMINAL
    }

    private static final Set<String> INT_FEATURES = new HashSet<>(Arrays.asList(
            METHODS, METHODS_CHARACTERS, METHODS_LINES, METHODS_PARAMETERS, FIELDS, PRIVATE_FIELDS, PUBLIC_FIELDS,
            LOCAL_VARIABLES, FIELDS_LENGTH, VARIABLES_LENGTH, CLASSES, INTERFACES, INNER_CLASSES, FORS, WHILES, IFS,
            ELSES, ELSE_IFS, TERNARY, STRING_CONSTANTS, INT_CONSTANTS, CHAR_CONSTANTS, LAMBDAS, BREAKS, CONTINUES,
            NULLS, LINE_COMMENTS, BLOCK_COMMENTS, JAVA_DOC_COMMENTS, TOTAL_LENGTH, LINES, TABS, SPACES, EMPTY_LINES,
            WHITESPACE_CHARS
    ));

    private static final Set<String> STRING_FEATURES = new HashSet<>(Arrays.asList(
            PROJECT, PATH, AST
    ));

    private static final Set<String> NOMINAL_FEATURES = new HashSet<>(Arrays.asList(
            TABS_LEAD_LINES, PUNCTUATION_BEFORE_BRACE
    ));

    private static final Set<String> RATIO_TO_TOTAL_FEATURES = new HashSet<>(Arrays.asList(
            RATIO_BLOCK_COMMENTS, RATIO_BREAKS, RATIO_CHAR_CONSTANTS, RATIO_CONTINUES, RATIO_ELSE_IFS, RATIO_ELSES,
            RATIO_FORS, RATIO_IFS, RATIO_INT_CONSTANTS, RATIO_JAVA_DOC_COMMENTS, RATIO_LAMBDAS,
            RATIO_LINE_COMMENTS, RATIO_NULLS, RATIO_SPACES, RATIO_STRING_CONSTANTS, RATIO_TABS, RATIO_TERNARY,
            RATIO_WHILES, RATIO_WHITESPACE_CHARS
    ));

    private static final Set<String> RATIO_TO_FIELDS_FEATURES = new HashSet<>(Arrays.asList(
            RATIO_PRIVATE_FIELDS, RATIO_PUBLIC_FIELDS
    ));

    private static final Set<String> RATIO_TO_LINES_FEATURES = new HashSet<>(Arrays.asList(
            RATIO_EMPTY_LINES
    ));

    private static final Set<String> AVG_TO_LINES_FEATURES = new HashSet<>(Arrays.asList(
            AVG_LINE_LENGTH
    ));

    private static final Set<String> AVG_TO_FIELDS_FEATURES = new HashSet<>(Arrays.asList(
            AVG_FIELDS_LENGTH
    ));

    private static final Set<String> AVG_TO_VARIABLES_FEATURES = new HashSet<>(Arrays.asList(
            AVG_VARIABLES_LENGTH
    ));

    private static final Set<String> AVG_TO_METHODS_FEATURES = new HashSet<>(Arrays.asList(
            AVG_METHODS_CHARACTERS, AVG_METHODS_LINES, AVG_METHODS_PARAMETERS
    ));

    private static final Set<String> STD_DEV_LINES_FEATURES = new HashSet<>(Arrays.asList(
            STD_DEV_LINE_LENGTH
    ));

    private static final Set<String> STD_DEV_METHODS_FEATURES = new HashSet<>(Arrays.asList(
            STD_DEV_METHODS_PARAMETERS
    ));

    private static final List<String> ALL_FEATURES = new ArrayList<>();

    private final Map<String, Object> values = new HashMap<>();

    public final List<Integer> linesLengths = new ArrayList<>();

    public final List<Integer> methodsParameters = new ArrayList<>();

    public int getIntFeature(String field) {
        if (!INT_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't an int field.");
        }
        return (Integer) values.get(field);
    }

    public String getStringFeature(String field) {
        if (!STRING_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a string field.");
        }
        return (String) values.get(field);
    }

    public String getNominalFeature(String field) {
        if (!NOMINAL_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a nominal field.");
        }
        return (String) values.get(field);
    }

    public double getRatioTotalFeature(String field) {
        if (!RATIO_TO_TOTAL_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a ratio to total field.");
        }
        String ancField = field.substring(5); // drop ratio
        return (double) values.get(ancField) / (double) values.get(TOTAL_LENGTH);
    }

    public double getRatioLinesFeature(String field) {
        if (!RATIO_TO_LINES_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a ratio to lines field.");
        }
        String ancField = field.substring(5); // drop ratio
        return (double) values.get(ancField) / (double) values.get(LINES);
    }

    public double getRatioFieldsFeature(String field) {
        if (!RATIO_TO_FIELDS_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a ratio to fields field.");
        }
        String ancField = field.substring(5); // drop ratio
        return (double) values.get(ancField) / (double) values.get(FIELDS);
    }

    public double getAvgFieldsFeature(String field) {
        if (!AVG_TO_FIELDS_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't an average fields field.");
        }
        String ancField = field.substring(3); // drop avg
        return (double) values.get(ancField) / (double) values.get(FIELDS);
    }

    public double getAvgVariablesFeature(String field) {
        if (!AVG_TO_VARIABLES_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't an average variables field.");
        }
        String ancField = field.substring(3); // drop avg
        return (double) values.get(ancField) / (double) values.get(LOCAL_VARIABLES);
    }

    public double getAvgLinesFeature(String field) {
        if (!AVG_TO_LINES_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't an average lines field.");
        }
        String ancField = field.substring(3); // drop avg
        return (double) values.get(ancField) / (double) values.get(LINES);
    }

    public double getAvgMethodsFeature(String field) {
        if (!AVG_TO_METHODS_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't an average methods field.");
        }
        String ancField = field.substring(3); // drop avg
        return (double) values.get(ancField) / (double) values.get(METHODS);
    }

    public double getStdDevLinesFeature(String field) {
        if (!STD_DEV_LINES_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a std dev lines field.");
        }
        String totalField = field.substring(6); // drop stdDev
        String avgField = "Avg" + field; // make avg field
        int total = getIntFeature(totalField);
        double avg = getAvgLinesFeature(avgField);
        if (total <= 1) return 0;
        double sum = 0;
        for (int len : linesLengths) {
            sum += (len - avg) * (len - avg);
        }
        return Math.sqrt(sum / (total - 1));
    }

    public double getStdDevMethodsFeature(String field) {
        if (!STD_DEV_METHODS_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a std dev methods field.");
        }
        String totalField = field.substring(6); // drop stdDev
        String avgField = "Avg" + field; // make avg field
        int total = getIntFeature(totalField);
        double avg = getAvgMethodsFeature(avgField);
        if (total <= 1) return 0;
        double sum = 0;
        for (int len : methodsParameters) {
            sum += (len - avg) * (len - avg);
        }
        return Math.sqrt(sum / (total - 1));
    }

    public void addToIntFeature(String field, int val) {
        if (!INT_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to add, " + field + " isn't an int field.");
        }
        int updatedVal = (Integer) values.get(field) + val;
        values.put(field, updatedVal);
    }

    public void addToStringFeature(String field, String val) {
        if (!STRING_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to add, " + field + " isn't a string field.");
        }
        String updatedVal = values.get(field) + val;
        values.put(field, updatedVal);
    }

    public void setNominalFeature(String field, String val) {
        if (!NOMINAL_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to set, " + field + " isn't a nominal field.");
        }
        values.put(field, val);
    }

    public static List<String> getListOfFeatures() {
        return ALL_FEATURES;
    }

    public static ValueType getType(String field) {
        if (INT_FEATURES.contains(field)) return ValueType.INT;
        if (STRING_FEATURES.contains(field)) return ValueType.STRING;
        if (NOMINAL_FEATURES.contains(field)) return ValueType.NOMINAL;
        if (RATIO_TO_TOTAL_FEATURES.contains(field)) return ValueType.RATIO_TOTAL;
        if (RATIO_TO_LINES_FEATURES.contains(field)) return ValueType.RATIO_LINES;
        if (RATIO_TO_FIELDS_FEATURES.contains(field)) return ValueType.RATIO_FIELDS;
        if (AVG_TO_FIELDS_FEATURES.contains(field)) return ValueType.AVG_FIELDS;
        if (AVG_TO_VARIABLES_FEATURES.contains(field)) return ValueType.AVG_VARIABLES;
        if (AVG_TO_LINES_FEATURES.contains(field)) return ValueType.AVG_LINES;
        if (AVG_TO_METHODS_FEATURES.contains(field)) return ValueType.AVG_METHODS;
        if (STD_DEV_METHODS_FEATURES.contains(field)) return ValueType.STD_DEV_METHODS;
        if (STD_DEV_LINES_FEATURES.contains(field)) return ValueType.STD_DEV_LINES;
        return null;
    }

    static {
        ALL_FEATURES.addAll(INT_FEATURES);
        ALL_FEATURES.addAll(STRING_FEATURES);
        ALL_FEATURES.addAll(NOMINAL_FEATURES);
    }

    public StatisticsHolder() {
        for (String field : INT_FEATURES) {
            values.put(field, 0);
        }

        for (String field : STRING_FEATURES) {
            values.put(field, "");
        }

        for (String field : NOMINAL_FEATURES) {
            values.put(field, "NaN");
        }
    }

}
