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

        // Parse the file
        FileInputStream in = new FileInputStream(path);
        CompilationUnit cu = JavaParser.parse(in);

        return getMethodsStatistics(cu).withAst(getXmlAstString(cu));
    }

    private static String getXmlAstString(CompilationUnit cu) {
        String xmlString = new XmlPrinter(true).output(cu);
        return addTabulationToXml(xmlString);
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

    private static StatisticsHolder getMethodsStatistics(CompilationUnit compilationUnit) {
        MethodVisitor visitor = new MethodVisitor();
        compilationUnit.accept(visitor,null);
        return StatisticsHolder.create(visitor.methodsNumber, visitor.totalCharacters, visitor.totalLines);
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
