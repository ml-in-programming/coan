import statitics.StatisticsCollector;
import statitics.StatisticsHolder;

import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        StatisticsHolder stats = StatisticsCollector.collectFromProject(Paths.get(System.getProperty("user.dir")));
        System.out.println(stats.getAst());
        System.out.println("Number of methods: " + stats.getMethods());
        System.out.println("Total number of characters in methods: " + stats.getMethodsCharacters());
        System.out.println("Total number of lines in methods: " + stats.getMethodsLines());
        System.out.println("Number of fields: " + stats.getFields());
        System.out.println("Number of private fields: " + stats.getPrivateFields());
        System.out.println("Number of public fields: " + stats.getPublicFields());
        System.out.println("Number of local variables: " + stats.getLocalVariables());
        System.out.println("Average length of field's name is " + (stats.getFieldsLength() / stats.getFields()));
        System.out.println("Average length of variable's name is " + (stats.getVariablesLength() / stats.getLocalVariables()));
    }
}
