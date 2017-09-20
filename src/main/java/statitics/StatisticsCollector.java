package statitics;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.XmlPrinter;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StatisticsCollector {

    /**
     * Collects information about the class stored in .java file.
     * @param path path to the .java file.
     * @return statistics about the class that is stored in that file.
     * @throws FileNotFoundException if there was no file at given path.
     */
    public static StatisticsHolder collectFromFile(String path) throws FileNotFoundException {
        StatisticsHolder result = new StatisticsHolder();

        // Parse the file
        FileInputStream in = new FileInputStream(path);
        CompilationUnit cu = JavaParser.parse(in);

        getXmlAstString(cu, result);
        getMethodsStatistics(cu, result);
        return result;
    }

    private static void getXmlAstString(CompilationUnit cu, StatisticsHolder result) {
        String xmlString = new XmlPrinter(true).output(cu);
        result.setAst(addTabulationToXml(xmlString));
    }

    private static String addTabulationToXml(String xmlString) {
        int count = 0;
        StringBuilder resultingString = new StringBuilder();
        for (int i = 0; i < xmlString.length(); i++) {
            char now = xmlString.charAt(i);
            if (now == '<') {
                if (xmlString.charAt(i + 1) == '/') {
                    count--;
                    appendTabs(resultingString, count);
                } else {
                    appendTabs(resultingString, count);
                    count++;
                }
            }
            resultingString.append(now);
            if (now == '>') {
                resultingString.append('\n');
            }
        }
        return resultingString.toString();
    }

    private static void appendTabs(StringBuilder string, int n) {
        for (int i = 0; i < n; i++) {
            string.append('\t');
        }
    }

    private static void getMethodsStatistics(CompilationUnit compilationUnit, StatisticsHolder result) {
        MethodVisitor methodCounter = new MethodVisitor();
        compilationUnit.accept(methodCounter,null);
        result.setMethodsNumber(methodCounter.methodsNumber);
        result.setTotalCharacters(methodCounter.totalCharacters);
        result.setTotalLines(methodCounter.totalLines);
    }

    /** Visitor that collects statistics about methods. */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        private int methodsNumber = 0;
        private int totalCharacters = 0;
        private int totalLines = 0;

        @Override
        public void visit(MethodDeclaration method, Void arg) {
            methodsNumber++;
            totalCharacters += method.toString().replaceAll("\\s+","").length();
            totalLines += StringUtils.countMatches(method.toString(), "\n") + 1;
            super.visit(method, arg);
        }
    }
}
