package statitics;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang3.StringUtils;

public class MethodCollector {

    public static void getStatistics(CompilationUnit compilationUnit, StatisticsHolder stats) {
        compilationUnit.accept(new MethodVisitor(), stats);
    }

    /** Visitor that collects statistics about methods. */
    private static class MethodVisitor extends VoidVisitorAdapter<StatisticsHolder> {

        @Override
        public void visit(MethodDeclaration method, StatisticsHolder stats) {
            AstCollector.getAst(method, stats);
            stats.addMethodsNumber(1);
            stats.addMethodsCharacters(method.toString().replaceAll("\\s+","").length());
            stats.addMethodsLines(StringUtils.countMatches(method.toString(), "\n") + 1);
            super.visit(method, stats);
        }
    }
}
