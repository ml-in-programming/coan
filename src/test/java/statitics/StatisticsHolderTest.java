package statitics;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StatisticsHolderTest {

    private static final int a = 13;
    private static final int b = 42;
    private static final String ast1 = "test";
    private static final String ast2 = "string";


    private final StatisticsHolder stats = new StatisticsHolder();

    @Test
    public void addAst() throws Exception {
        stats.addAst(ast1);
        stats.addAst(ast2);
        assertEquals(stats.getAst(), ast1 + "\n" + ast2);
    }

    @Test
    public void addMethods() throws Exception {
        stats.addMethods(a);
        stats.addMethods(b);
        assertEquals(stats.getMethods(), a + b);
    }

    @Test
    public void addMethodsCharacters() throws Exception {
        stats.addMethodsCharacters(a);
        stats.addMethodsCharacters(b);
        assertEquals(stats.getMethodsCharacters(), a + b);
    }

    @Test
    public void addMethodsLines() throws Exception {
        stats.addMethodsLines(a);
        stats.addMethodsLines(b);
        assertEquals(stats.getMethodsLines(), a + b);
    }

    @Test
    public void addFields() throws Exception {
        stats.addFields(a);
        stats.addFields(b);
        assertEquals(stats.getFields(), a + b);
    }

    @Test
    public void addPrivateFields() throws Exception {
        stats.addPrivateFields(a);
        stats.addPrivateFields(b);
        assertEquals(stats.getPrivateFields(), a + b);
    }

    @Test
    public void addPublicFields() throws Exception {
        stats.addPublicFields(a);
        stats.addPublicFields(b);
        assertEquals(stats.getPublicFields(), a + b);
    }

    @Test
    public void addLocalVariables() throws Exception {
        stats.addLocalVariables(a);
        stats.addLocalVariables(b);
        assertEquals(stats.getLocalVariables(), a + b);
    }

    @Test
    public void addFieldsLength() throws Exception {
        stats.addFieldsLength(a);
        stats.addFieldsLength(b);
        assertEquals(stats.getFieldsLength(), a + b);
    }

    @Test
    public void addVariablesLength() throws Exception {
        stats.addVariablesLength(a);
        stats.addVariablesLength(b);
        assertEquals(stats.getVariablesLength(), a + b);
    }

    @Test
    public void addClasses() throws Exception {
        stats.addClasses(a);
        stats.addClasses(b);
        assertEquals(stats.getClasses(), a + b);
    }

    @Test
    public void addInterfaces() throws Exception {
        stats.addInterfaces(a);
        stats.addInterfaces(b);
        assertEquals(stats.getInterfaces(), a + b);
    }

    @Test
    public void addInnerClasses() throws Exception {
        stats.addInnerClasses(a);
        stats.addInnerClasses(b);
        assertEquals(stats.getInnerClasses(), a + b);
    }

}