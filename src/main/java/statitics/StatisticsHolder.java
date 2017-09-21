package statitics;

public class StatisticsHolder {

    /** Visual representation of AST in xml-format. */
    private String ast = "";
    /** Number of methods. */
    private int methodsNumber = 0;
    /** Number of non-whitespace characters in methods. Includes methods' declarations. */
    private int totalMethodsCharacters = 0;
    /** Number of lines in methods. Includes methods' declarations. */
    private int totalMethodsLines = 0;

     public String getAst() {
        return ast;
    }

    public int getMethodsNumber() {
        return methodsNumber;
    }

    public int getTotalMethodsCharacters() {
        return totalMethodsCharacters;
    }

    public int getTotalMethodsLines() {
        return totalMethodsLines;
    }

    public void addAst(String ast) {
        this.ast += "\n" + ast;
    }

    public void addMethodsNumber(int methodsNumber) {
       this.methodsNumber += methodsNumber;
    }

    public void addMethodsCharacters(int totalMethodsCharacters) {
        this.totalMethodsCharacters += totalMethodsCharacters;
    }

    public void addMethodsLines(int totalMethodsLines) {
        this.totalMethodsLines += totalMethodsLines;
    }

}
