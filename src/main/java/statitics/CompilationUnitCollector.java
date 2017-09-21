package statitics;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class CompilationUnitCollector {

    /**
     * Collects different stats from {@link CompilationUnit}. You can find list of stats in {@link StatisticsHolder}.
     * @param compilationUnit {@link CompilationUnit} from which statistics will be collected.
     * @param stats {@link StatisticsHolder} in which all the stats will be saved.
     * @param filter predicate that returns true if you want to take a method into account.
     */
    public static void getStatistics(
            CompilationUnit compilationUnit, StatisticsHolder stats, Predicate<MethodDeclaration> filter) {
        compilationUnit.accept(new MethodVisitor(filter), stats);
    }

    /** Visitor that collects statistics about classes, methods etc. */
    private static class MethodVisitor extends VoidVisitorAdapter<StatisticsHolder> {

        private Predicate<MethodDeclaration> filter = m -> true;

        private MethodVisitor() {}

        private MethodVisitor(Predicate<MethodDeclaration> filter) {
            this.filter = filter;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration declaration, StatisticsHolder stats) {
            if (declaration.isInterface()) {
                stats.addInterfaces(1);
            } else if (declaration.isInnerClass()) {
                stats.addInnerClasses(1);
            } else {
                stats.addClasses(1);
            }
            super.visit(declaration, stats);
        }

        @Override
        public void visit(MethodDeclaration method, StatisticsHolder stats) {
            if (!filter.test(method)) {
                return;
            }
            AstCollector.getAst(method, stats);
            stats.addMethodsNumber(1);
            stats.addMethodsCharacters(method.toString().replaceAll("\\s+","").length());
            stats.addMethodsLines(StringUtils.countMatches(method.toString(), "\n") + 1);
            super.visit(method, stats);
        }

        @Override
        public void visit(FieldDeclaration declaration, StatisticsHolder stats) {
            int numberOfDeclared = declaration.getVariables().size();
            stats.addFields(numberOfDeclared);
            if (declaration.isPrivate()) {
                stats.addPrivateFields(numberOfDeclared);
            }
            if (declaration.isPublic()) {
                stats.addPublicFields(numberOfDeclared);
            }
            stats.addFieldsLength(getVariablesLength(declaration.getVariables()));
            super.visit(declaration, stats);
        }

        @Override
        public void visit(VariableDeclarationExpr declaration, StatisticsHolder stats) {
            stats.addLocalVariables(declaration.getVariables().size());
            stats.addVariablesLength(getVariablesLength(declaration.getVariables()));
            super.visit(declaration, stats);
        }

        private static int getVariablesLength(NodeList<VariableDeclarator> variables) {
            return variables.stream().mapToInt(variable -> variable.getNameAsString().length()).sum();
        }
    }
}
