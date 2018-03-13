package statitics;

import static statitics.StatisticsHolder.*;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class CompilationUnitCollector {

    /**
     * Collects different stats from {@link CompilationUnit}.
     * You can find list of stats in {@link StatisticsHolder}.
     *
     * @param compilationUnit {@link CompilationUnit} from which statistics will be collected.
     * @param stats           {@link StatisticsHolder} in which all the stats will be saved.
     * @param filter          predicate that returns true if you want to take a method into account.
     */
    public static void getStatistics(
            CompilationUnit compilationUnit, StatisticsHolder stats, Predicate<MethodDeclaration> filter) {
        compilationUnit.accept(new MethodVisitor(filter), stats);
    }

    /**
     * Visitor that collects statistics about classes, methods etc.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<StatisticsHolder> {

        private Predicate<MethodDeclaration> filter = m -> true;

        private MethodVisitor() {
        }

        @Override
        public void visit(BlockComment n, StatisticsHolder stats) {
            stats.addToIntFeature(BLOCK_COMMENTS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(BreakStmt n, StatisticsHolder stats) {
            stats.addToIntFeature(BREAKS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(CharLiteralExpr n, StatisticsHolder stats) {
            stats.addToIntFeature(CHAR_CONSTANTS, 1);
            super.visit(n, stats);
        }

        private MethodVisitor(Predicate<MethodDeclaration> filter) {
            this.filter = filter;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration declaration, StatisticsHolder stats) {
            if (declaration.isInterface()) {
                stats.addToIntFeature(INTERFACES, 1);
            } else if (declaration.isInnerClass()) {
                stats.addToIntFeature(INNER_CLASSES, 1);
            } else {
                stats.addToIntFeature(CLASSES, 1);
            }
            super.visit(declaration, stats);
        }

        @Override
        public void visit(ConditionalExpr n, StatisticsHolder stats) {
            stats.addToIntFeature(TERNARY, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(ContinueStmt n, StatisticsHolder stats) {
            stats.addToIntFeature(CONTINUES, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(MethodDeclaration method, StatisticsHolder stats) {
            if (!filter.test(method)) {
                return;
            }
            AstCollector.getAst(method, stats);
            stats.addToIntFeature(METHODS, 1);
            stats.addToIntFeature(METHODS_CHARACTERS, method.toString().replaceAll("\\s+", "").length());
            stats.addToIntFeature(METHODS_LINES, StringUtils.countMatches(method.toString(), "\n") + 1);
            stats.addToIntFeature(METHODS_PARAMETERS, method.getParameters().size());
            super.visit(method, stats);
        }

        @Override
        public void visit(NullLiteralExpr n, StatisticsHolder stats) {
            stats.addToIntFeature(NULLS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(StringLiteralExpr n, StatisticsHolder stats) {
            stats.addToIntFeature(STRING_CONSTANTS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(FieldDeclaration declaration, StatisticsHolder stats) {
            int numberOfDeclared = declaration.getVariables().size();
            stats.addToIntFeature(FIELDS, numberOfDeclared);
            if (declaration.isPrivate()) {
                stats.addToIntFeature(PRIVATE_FIELDS, numberOfDeclared);
            }
            if (declaration.isPublic()) {
                stats.addToIntFeature(PUBLIC_FIELDS, numberOfDeclared);
            }
            stats.addToIntFeature(VARIABLES_LENGTH, getVariablesLength(declaration.getVariables()));
            super.visit(declaration, stats);
        }

        @Override
        public void visit(ForeachStmt n, StatisticsHolder stats) {
            stats.addToIntFeature(FORS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(ForStmt n, StatisticsHolder stats) {
            stats.addToIntFeature(FORS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(IfStmt n, StatisticsHolder stats) {
            stats.addToIntFeature(IFS, 1);
            if (n.getElseStmt().isPresent()) {
                if (n.getElseStmt().get() instanceof IfStmt) {
                    stats.addToIntFeature(ELSE_IFS, 1);
                } else {
                    stats.addToIntFeature(ELSES, 1);
                }
            }
            super.visit(n, stats);
        }


        @Override
        public void visit(IntegerLiteralExpr n, StatisticsHolder stats) {
            stats.addToIntFeature(INT_CONSTANTS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(JavadocComment n, StatisticsHolder stats) {
            stats.addToIntFeature(JAVA_DOC_COMMENTS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(LineComment n, StatisticsHolder stats) {
            stats.addToIntFeature(LINE_COMMENTS, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(VariableDeclarationExpr declaration, StatisticsHolder stats) {
            stats.addToIntFeature(LOCAL_VARIABLES, declaration.getVariables().size());
            stats.addToIntFeature(VARIABLES_LENGTH, getVariablesLength(declaration.getVariables()));
            super.visit(declaration, stats);
        }

        @Override
        public void visit(WhileStmt n, StatisticsHolder stats) {
            stats.addToIntFeature(WHILES, 1);
            super.visit(n, stats);
        }

        @Override
        public void visit(LambdaExpr n, StatisticsHolder stats) {
            stats.addToIntFeature(LAMBDAS, 1);
            super.visit(n, stats);
        }


        private static int getVariablesLength(NodeList<VariableDeclarator> variables) {
            return variables.stream().mapToInt(variable -> variable.getNameAsString().length()).sum();
        }
    }
}
