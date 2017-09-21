package statitics;

public class StatisticsHolder {

    /** Visual representation of AST in xml-format. */
    private String ast = "";
    /** Number of methods. */
    private int methods = 0;
    /** Number of non-whitespace characters in methods. Includes methods' declarations. */
    private int methodsCharacters = 0;
    /** Number of lines in methods. Includes methods' declarations. */
    private int methodsLines = 0;
    /** Number of fields. */
    private int fields = 0;
    /** Number of private fields. */
    private int privateFields = 0;
    /** Number of public fields. */
    private int publicFields;
    /** Number of local variables. */
    private int localVariables = 0;
    /** Total length of all fields. */
    private int fieldsLength = 0;
    /** Total length of all local variables. */
    private int variablesLength = 0;

     public String getAst() {
        return ast;
    }

    public int getMethods() {
        return methods;
    }

    public int getMethodsCharacters() {
        return methodsCharacters;
    }

    public int getMethodsLines() {
        return methodsLines;
    }

    public int getFields() {
        return fields;
    }

    public int getPrivateFields() {
        return privateFields;
    }

    public int getPublicFields() {
        return publicFields;
    }

    public int getLocalVariables() {
        return localVariables;
    }

    public int getFieldsLength() {
        return fieldsLength;
    }

    public int getVariablesLength() {
        return variablesLength;
    }

    public void addAst(String ast) {
        this.ast += "\n" + ast;
    }

    public void addMethodsNumber(int methods) {
        this.methods += methods;
    }

    public void addMethodsCharacters(int methodsCharacters) {
        this.methodsCharacters += methodsCharacters;
    }

    public void addMethodsLines(int methodsLines) {
        this.methodsLines += methodsLines;
    }

    public void addFields(int fields) {
        this.fields += fields;
    }

    public void addPrivateFields(int privateFields) {
        this.privateFields += privateFields;
    }

    public void addPublicFields(int publicFields) {
        this.publicFields += publicFields;
    }

    public void addLocalVariables(int localVariables) {
        this.localVariables += localVariables;
    }

    public void addFieldsLength(int fieldsLength) {
        this.fieldsLength += fieldsLength;
    }

    public void addVariablesLength(int variablesLength) {
        this.variablesLength += variablesLength;
    }
}
