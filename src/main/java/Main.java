import statitics.StatisticsCollector;
import statitics.StatisticsHolder;

public class Main {

    public static void main(String[] args) throws Exception {
        StatisticsHolder stats = StatisticsCollector.collectFromFile("src/main/java/samples/Basic.java");
        System.out.println(stats.getAst());
        System.out.println("Number of methods: " + stats.getMethodsNumber());
        System.out.println("Total number of characters in methods: " + stats.getTotalMethodsCharacters());
        System.out.println("Total number of lines in methods: " + stats.getTotalMethodsLines());
    }
}
