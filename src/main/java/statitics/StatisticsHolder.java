package statitics;

public class StatisticsHolder {

    /** Visual representation of AST in xml-format. */
    private String ast;
    /** Number of methods. */
    private int methodsNumber;
    /** Number of non-whitespace characters in methods. Includes methods' declarations. */
    private int totalCharacters;
    /** Number of lines in methods. Includes methods' declarations. */
    private int totalLines;

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }

    public int getMethodsNumber() {
        return methodsNumber;
    }

    public void setMethodsNumber(int methodsNumber) {
        this.methodsNumber = methodsNumber;
    }

    public int getTotalCharacters() {
        return totalCharacters;
    }

    public void setTotalCharacters(int totalCharacters) {
        this.totalCharacters = totalCharacters;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }
}
