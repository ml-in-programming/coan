package statitics;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
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
            return variables.stream().mapToInt(variable -> variable.getName().getIdentifier().length()).sum();
        }
    }
}
