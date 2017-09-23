package statitics;

import static org.junit.Assert.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.PrimitiveType;
import org.junit.BeforeClass;
import org.junit.Test;


public class CompilationUnitCollectorTest {

    private static final int numberOfClasses = 2;
    private static final int numberOfInterfaces = 3;
    private static final int numberOfPrivateFields = 4;
    private static final int numberOfPublicFields = 5;
    private static final int numberOfVariables = 6;
    private static final int numberOfMethods = 3;

    private static ClassOrInterfaceDeclaration typeClass;
    private static ClassOrInterfaceDeclaration typeInterface;
    private static MethodDeclaration method;

    private static CompilationUnit compilationUnit;


    @BeforeClass
    public static void setUpClass() {
        compilationUnit = new CompilationUnit();
        typeClass = compilationUnit.addClass("GeneratedClass1");
        for (int i = 2; i <= numberOfClasses; i++) {
            compilationUnit.addClass("GeneratedClass" + i);
        }
        typeInterface = compilationUnit.addInterface("GeneratedInterface1");
        for (int i = 2; i <= numberOfInterfaces; i++) {
            compilationUnit.addInterface("GeneratedInterface" + i);
        }
        for (int i = 1; i <= numberOfPrivateFields; i++) {
            typeClass.addField(new PrimitiveType(), "privateField" + i, Modifier.PRIVATE);
        }
        for (int i = 1; i <= numberOfPublicFields; i++) {
            typeClass.addField(new PrimitiveType(), "publicField" + i, Modifier.PUBLIC);
        }
        method = typeClass.addMethod("method1");
        for (int i = 2; i <= numberOfMethods; i++) {
            typeClass.addMethod("method" + i);
        }
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        for (int i = 1; i <= numberOfVariables; i++) {
            block.addStatement(new VariableDeclarationExpr(new PrimitiveType(), "variable" + i));
        }
    }

    @Test
    public void getClassOrInterfaceStatistics() {
        StatisticsHolder stats = new StatisticsHolder();
        CompilationUnitCollector.getStatistics(compilationUnit, stats, m -> false);
        assertEquals(stats.getClasses(), numberOfClasses);
        assertEquals(stats.getInterfaces(), numberOfInterfaces);
    }

    @Test
    public void getFieldsStatistics() {
        StatisticsHolder stats = new StatisticsHolder();
        CompilationUnitCollector.getStatistics(compilationUnit, stats, m -> false);
        assertEquals(stats.getFields(), numberOfPrivateFields + numberOfPublicFields);
        assertEquals(stats.getPublicFields(), numberOfPublicFields);
        assertEquals(stats.getPrivateFields(), numberOfPrivateFields);
        assertEquals(stats.getFieldsLength(),
                "privateFieldN".length() * numberOfPrivateFields +
                        "publicFieldN".length() * numberOfPublicFields);
    }

    @Test
    public void getMethodsStatistics() {
        StatisticsHolder stats = new StatisticsHolder();
        CompilationUnitCollector.getStatistics(compilationUnit, stats, m -> true);
        assertEquals(stats.getMethods(), numberOfMethods);
        assertEquals(stats.getMethodsLines(), numberOfMethods * 2 + numberOfVariables);
    }

    @Test
    public void filteringWorks() {
        StatisticsHolder stats = new StatisticsHolder();
        CompilationUnitCollector.getStatistics(compilationUnit, stats, m -> {
            String name = m.getNameAsString();
            return name.charAt(name.length() - 1) <= '2';
        });
        assertEquals(stats.getMethods(), 2);
    }

    @Test
    public void getVariablesStatistics() {
        StatisticsHolder stats = new StatisticsHolder();
        CompilationUnitCollector.getStatistics(compilationUnit, stats, m -> true);
        assertEquals(stats.getLocalVariables(), numberOfVariables);
        assertEquals(stats.getVariablesLength(), "variableN".length() * numberOfVariables);
    }
}