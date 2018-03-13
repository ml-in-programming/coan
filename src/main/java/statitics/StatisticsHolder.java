package statitics;

import java.util.*;

/**
 * To add a feature you should add a field with its name and add it to a list according to its type (e.g. create field
 * named SYMBOL with value "Symbol" and add it to INT_FEATURES).
 */
public class StatisticsHolder {
    /**
     * Path to file.
     */
    public static String PATH = "Path";
    /**
     * Project's name.
     */
    public static String PROJECT = "Project";
    /**
     * Visual representation of AST in xml-format.
     */
    public static String AST = "Ast";
    /**
     * Number of methods.
     */
    public static final String METHODS = "Method";
    /**
     * Number of non-whitespace characters in methods. Includes methods' declarations.
     */
    public static final String METHODS_CHARACTERS = "MethodsCharacters";
    /**
     * Number of lines in methods. Includes methods' declarations.
     */
    public static final String METHODS_LINES = "MethodsLines";
    /**
     * Number of parameters in methods.
     */
    public static final String METHODS_PARAMETERS = "MethodsParameters";
    /**
     * Number of fields.
     */
    public static final String FIELDS = "Fields";
    /**
     * Number of public fields.
     */
    public static final String PRIVATE_FIELDS = "PrivateFields";
    /**
     * Number of public fields.
     */
    public static final String PUBLIC_FIELDS = "PublicFields";
    /**
     * Number of local variables.
     */
    public static final String LOCAL_VARIABLES = "LocalVariables";
    /**
     * Total length of all fields.
     */
    public static final String FIELDS_LENGTH = "FieldsLength";
    /**
     * Total length of all local variables.
     */
    public static final String VARIABLES_LENGTH = "VariablesLength";
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
    /**
     * Number of while cycles.
     */
    public static final String WHILES = "While";
    /**
     * Number of if statements.
     */
    public static final String IFS = "If";
    /**
     * Number of else statements.
     */
    public static final String ELSES = "Else";
    /**
     * Number of "else if" statements.
     */
    public static final String ELSE_IFS = "ElseIf";
    /**
     * Number of ternary operators (? :).
     */
    public static final String TERNARY = "TernaryOperator";
    /**
     * Number of string literals.
     */
    public static final String STRING_CONSTANTS = "StringConstant";
    /**
     * Number of integer constants.
     */
    public static final String INT_CONSTANTS = "IntConstant";
    /**
     * Number of char constants.
     */
    public static final String CHAR_CONSTANTS = "CharConstant";
    /**
     * Number of lambda functions.
     */
    public static final String LAMBDAS = "Lambda";
    /**
     * Number of break statements.
     */
    public static final String BREAKS = "Break";
    /**
     * Number of continue statements.
     */
    public static final String CONTINUES = "Continue";
    /**
     * Number of nulls.
     */
    public static final String NULLS = "Null";
    /**
     * Number of comments starting with //
     */
    public static final String LINE_COMMENTS = "LineComment";
    /**
     * Number of comments starting with /*.
     */
    public static final String BLOCK_COMMENTS = "BlockComment";
    /**
     * Number of java-doc comments.
     */
    public static final String JAVA_DOC_COMMENTS = "JavaDocComment";
    /**
     * Length of file in characters.
     */
    public static final String TOTAL_LENGTH = "TotalLength";
    /**
     * Length of file in lines.
     */
    public static final String LINES = "Line";
    /**
     * Number of \t symbols in file.
     */
    public static final String TABS = "Tab";
    /**
     * Length of spaces in file.
     */
    public static final String SPACES = "Space";
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
    /**
     * Number of whitespace characters.
     */
    public static final String WHITESPACE_CHARS = "Whitespace";

    public enum ValueType {
        INT,
        STRING,
        NOMINAL
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

    private static final List<String> ALL_FEATURES = new ArrayList<>();

    private final Map<String, Object> values = new HashMap<>();

    public int getIntFeature(String field) {
        if (!INT_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't an int field.");
        }
        return (Integer)values.get(field);
    }

    public String getStringFeature(String field) {
        if (!STRING_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a string field.");
        }
        return (String)values.get(field);
    }

    public String getNominalFeature(String field) {
        if (!NOMINAL_FEATURES.contains(field)) {
            throw new IllegalArgumentException("Unable to get, " + field + " isn't a nominal field.");
        }
        return (String)values.get(field);
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
