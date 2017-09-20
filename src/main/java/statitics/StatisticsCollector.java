package statitics;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.XmlPrinter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StatisticsCollector {

    public static StatisticsHolder collectFromFile(String path) throws FileNotFoundException {
        StatisticsHolder result = new StatisticsHolder();

        // Parse the file
        FileInputStream in = new FileInputStream(path);
        CompilationUnit cu = JavaParser.parse(in);

        result.setAst(getXmlAstString(cu));
        result.setMethodsNumber(getMethodsNumber(cu));
        return result;
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

    private static int getMethodsNumber(CompilationUnit compilationUnit) {
        MethodVisitor methodCounter = new MethodVisitor();
        compilationUnit.accept(methodCounter,null);
        return methodCounter.methodsNumber;
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        private int methodsNumber = 0;

        @Override
        public void visit(MethodDeclaration n, Void arg) {
            methodsNumber++;
            super.visit(n, arg);
        }
    }
}
