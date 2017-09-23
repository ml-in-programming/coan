package statitics;

import static org.junit.Assert.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.type.PrimitiveType;
import org.junit.BeforeClass;
import org.junit.Test;


public class CompilationUnitCollectorTest {

    private static final int numberOfClasses = 2;
    private static final int numberOfInterfaces = 3;
    private static final int numberOfPrivateFields = 4;
    private static final int numberOfPublicFields = 5;

    private static FieldDeclaration privateField;
    private static FieldDeclaration publicField;
    private static ClassOrInterfaceDeclaration typeClass;
    private static ClassOrInterfaceDeclaration typeInterface;

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
        privateField = typeClass.addField(new PrimitiveType(), "privateField1", Modifier.PRIVATE);
        for (int i = 2; i <= numberOfPrivateFields; i++) {
            typeClass.addField(new PrimitiveType(), "privateField" + i, Modifier.PRIVATE);
        }
        publicField = typeClass.addField(new PrimitiveType(), "publicField1", Modifier.PUBLIC);
        for (int i = 2; i <= numberOfPublicFields; i++) {
            typeClass.addField(new PrimitiveType(), "publicField" + i, Modifier.PUBLIC);
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
}