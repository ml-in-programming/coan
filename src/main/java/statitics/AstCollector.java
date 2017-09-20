package statitics;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.printer.XmlPrinter;

public class AstCollector {

    public static StatisticsHolder getAst(MethodDeclaration method, StatisticsHolder stats) {
        String xmlString = new XmlPrinter(true).output(method);
        return stats.addAst(method.getName() + ":\n" + addTabulationToXml(xmlString));
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
}
