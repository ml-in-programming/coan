package statitics;

// TODO: implement map with keys instead of fields.
public class StatisticsHolder {

    public String path = "";
    /** Visual representation of AST in xml-format. */
    public String ast = "";
    /** Number of methods. */
    public int methods = 0;
    /** Number of non-whitespace characters in methods. Includes methods' declarations. */
    public int methodsCharacters = 0;
    /** Number of lines in methods. Includes methods' declarations. */
    public int methodsLines = 0;
    /**
     * Number of parameters in methods.
     */
    public int methodsParameters = 0;
    /** Number of fields. */
    public int fields = 0;
    /** Number of public fields. */
    public int privateFields = 0;
    /**
     * Number of public fields.
     */
    public int publicFields;
    /** Number of local variables. */
    public int localVariables = 0;
    /** Total length of all fields. */
    public int fieldsLength = 0;
    /** Total length of all local variables. */
    public int variablesLength = 0;
    /** Number of classes. */
    public int classes = 0;
    /** Number of interfaces. */
    public int interfaces = 0;
    /** Number of inner classes. */
    public int innerClasses = 0;
    /**
     * Number of for cycles.
     */
    public int fors = 0;
    /**
     * Number of while cycles.
     */
    public int whiles = 0;
    /**
     * Number of if statements.
     */
    public int ifs = 0;
    /**
     * Number of else statements.
     */
    public int elses = 0;
    /**
     * Number of "else if" statements.
     */
    public int elseIfs = 0;
    /**
     * Number of ternary operators (? :).
     */
    public int ternary = 0;
    /**
     * Number of string literals.
     */
    public int stringConstants = 0;
    /**
     * Number of integer constants.
     */
    public int intConstants = 0;
    /**
     * Number of char constants.
     */
    public int charConstants = 0;
    /**
     * Number of lambda functions.
     */
    public int lambdas = 0;
    /**
     * Number of break statements.
     */
    public int breaks = 0;
    /**
     * Number of continue statements.
     */
    public int continues = 0;
    /**
     * Number of nulls.
     */
    public int nulls = 0;
    /**
     * Number of comments starting with //
     */
    public int lineComments = 0;
    /**
     * Number of comments starting with /*.
     */
    public int blockComments = 0;
    /**
     * Number of java-doc comments.
     */
    public int javaDocComments = 0;
    /**
     * Length of file in characters.
     */
    public int totalLength = 0;
    /**
     * Length of file in lines.
     */
    public int lines = 0;
    /**
     * Number of \t symbols in file.
     */
    public int tabs = 0;
    /**
     * Length of spaces in file.
     */
    public int spaces = 0;
    /**
     * True if most of lines in the file start with tabs opposed to spaces .
     */
    public boolean tabsLeadLines;
    /**
     * Has 3 options: "new line", "space" and "other" depending on whether most of "{" are preceded by new line symbol,
     * space or something else .
     **/
    public String punctuationBeforeBrace;
    /**
     * Number of empty lines.
     */
    public int emptyLines = 0;
    /**
     * Number of whitespace characters.
     */
    public int whitespaceCharacters = 0;
}
