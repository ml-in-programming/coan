package statitics;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang3.StringUtils;

public class MethodCollector {

    public static StatisticsHolder getStatistics(CompilationUnit compilationUnit, StatisticsHolder stats) {
        MethodVisitor visitor = new MethodVisitor(stats);
        compilationUnit.accept(visitor,null);
        return visitor.stats;
    }

    /** Visitor that collects statistics about methods. */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {

        private StatisticsHolder stats;

        MethodVisitor(StatisticsHolder stats) {
            this.stats = stats;
        }

        @Override
        public void visit(MethodDeclaration method, Void arg) {
            stats = AstCollector.getAst(method, stats
                    .addMethodsNumber(1)
                    .addMethodsCharacters(method.toString().replaceAll("\\s+","").length())
                    .addMethodsLines(StringUtils.countMatches(method.toString(), "\n") + 1));
            super.visit(method, arg);
        }
    }
}
