package statitics;

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

        @Override
        public void visit(BlockComment n, StatisticsHolder stats) {
            stats.blockComments++;
            super.visit(n, stats);
        }

        @Override
        public void visit(BreakStmt n, StatisticsHolder stats) {
            stats.breaks++;
            super.visit(n, stats);
        }

        @Override
        public void visit(CharLiteralExpr n, StatisticsHolder stats) {
            stats.charConstants++;
            super.visit(n, stats);
        }

        private MethodVisitor(Predicate<MethodDeclaration> filter) {
            this.filter = filter;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration declaration, StatisticsHolder stats) {
            if (declaration.isInterface()) {
                stats.interfaces++;
            } else if (declaration.isInnerClass()) {
                stats.innerClasses++;
            } else {
                stats.classes++;
            }
            super.visit(declaration, stats);
        }

        @Override
        public void visit(ConditionalExpr n, StatisticsHolder stats) {
            stats.ternary++;
            super.visit(n, stats);
        }

        @Override
        public void visit(ContinueStmt n, StatisticsHolder stats) {
            stats.continues++;
            super.visit(n, stats);
        }

        @Override
        public void visit(MethodDeclaration method, StatisticsHolder stats) {
            if (!filter.test(method)) {
                return;
            }
            AstCollector.getAst(method, stats);
            stats.methods++;
            stats.methodsCharacters += method.toString().replaceAll("\\s+", "").length();
            stats.methodsLines += StringUtils.countMatches(method.toString(), "\n") + 1;
            stats.methodsParameters += method.getParameters().size();
            super.visit(method, stats);
        }

        @Override
        public void visit(NullLiteralExpr n, StatisticsHolder stats) {
            stats.nulls++;
            super.visit(n, stats);
        }

        @Override
        public void visit(StringLiteralExpr n, StatisticsHolder stats) {
            stats.stringConstants++;
            super.visit(n, stats);
        }

        @Override
        public void visit(FieldDeclaration declaration, StatisticsHolder stats) {
            int numberOfDeclared = declaration.getVariables().size();
            stats.fields += numberOfDeclared;
            if (declaration.isPrivate()) {
                stats.privateFields += numberOfDeclared;
            }
            if (declaration.isPublic()) {
                stats.publicFields += numberOfDeclared;
            }
            stats.fieldsLength += getVariablesLength(declaration.getVariables());
            super.visit(declaration, stats);
        }

        @Override
        public void visit(ForeachStmt n, StatisticsHolder stats) {
            stats.fors++;
            super.visit(n, stats);
        }

        @Override
        public void visit(ForStmt n, StatisticsHolder stats) {
            stats.fors++;
            super.visit(n, stats);
        }

        @Override
        public void visit(IfStmt n, StatisticsHolder stats) {
            stats.ifs++;
            if (n.getElseStmt().isPresent()) {
                if (n.getElseStmt().get() instanceof IfStmt) {
                    stats.elseIfs++;
                } else {
                    stats.elses++;
                }
            }
            super.visit(n, stats);
        }


        @Override
        public void visit(IntegerLiteralExpr n, StatisticsHolder stats) {
            stats.intConstants++;
            super.visit(n, stats);
        }

        @Override
        public void visit(JavadocComment n, StatisticsHolder stats) {
            stats.javaDocComments++;
            super.visit(n, stats);
        }

        @Override
        public void visit(LineComment n, StatisticsHolder stats) {
            stats.lineComments++;
            super.visit(n, stats);
        }

        @Override
        public void visit(VariableDeclarationExpr declaration, StatisticsHolder stats) {
            stats.localVariables += declaration.getVariables().size();
            stats.variablesLength += getVariablesLength(declaration.getVariables());
            super.visit(declaration, stats);
        }

        @Override
        public void visit(WhileStmt n, StatisticsHolder stats) {
            stats.whiles++;
            super.visit(n, stats);
        }

        @Override
        public void visit(LambdaExpr n, StatisticsHolder stats) {
            stats.lambdas++;
            super.visit(n, stats);
        }


        private static int getVariablesLength(NodeList<VariableDeclarator> variables) {
            return variables.stream().mapToInt(variable -> variable.getNameAsString().length()).sum();
        }
    }
}
