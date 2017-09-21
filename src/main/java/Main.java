import statitics.StatisticsCollector;
import statitics.StatisticsHolder;

import java.nio.file.Paths;

/**
 * Demonstration of usage of the project.
 * Collects information about this project, filtering out methods starting with "get", "set" and "add".
 */
public class Main {

    public static void main(String[] args) throws Exception {
        StatisticsHolder stats = StatisticsCollector.collectFromProject(
                Paths.get(System.getProperty("user.dir")),
                m -> {
                    String name = m.getNameAsString();
                    return !name.startsWith("get") && !name.startsWith("set") && !name.startsWith("add");
                });
        System.out.println(stats.getAst());
        System.out.println("Number of classes: " + stats.getClasses());
        System.out.println("Number of inner classes: " + stats.getInnerClasses());
        System.out.println("Number of interfaces: " + stats.getInterfaces());
        System.out.println("Number of methods: " + stats.getMethods());
        System.out.println("Total number of characters in methods: " + stats.getMethodsCharacters());
        System.out.println("Average number of characters per method: " + (stats.getMethodsCharacters() / stats.getMethods()));
        System.out.println("Total number of lines in methods: " + stats.getMethodsLines());
        System.out.println("Average number of lines per method: " + (stats.getMethodsLines() / stats.getMethods()));
        System.out.println("Number of fields: " + stats.getFields());
        System.out.println("Number of private fields: " + stats.getPrivateFields());
        System.out.println("Number of public fields: " + stats.getPublicFields());
        System.out.println("Number of local variables: " + stats.getLocalVariables());
        System.out.println("Average length of field's name is " + (stats.getFieldsLength() / stats.getFields()));
        System.out.println("Average length of variable's name is " + (stats.getVariablesLength() / stats.getLocalVariables()));
    }
}
