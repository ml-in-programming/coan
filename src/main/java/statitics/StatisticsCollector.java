package statitics;

import static statitics.StatisticsHolder.PATH;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StatisticsCollector {

    public static List<StatisticsHolder> collectFromProject(Path path) throws IOException {
        return collectFromProject(path, m -> true);
    }

    /**
     * Recursively collects information about classes stored in .java files in directory and it's subdirectories.
     * @param path path to the root of project.
     * @param filter returns true if you want to take the method into account.
     * @return cumulative statistics about classes stored in the project.
     */
    public static List<StatisticsHolder> collectFromProject(Path path, Predicate<MethodDeclaration> filter)
            throws IOException {
        List<StatisticsHolder> stats = new ArrayList<>();
        Files.walk(path).forEach(p -> {
            if (p.getFileName().toString().endsWith(".java")) {
                StatisticsHolder stat = new StatisticsHolder();
                try {
                    collectFromFile(p, stat, filter);
                    stat.addToStringFeature(PATH, p.toString());
                    stats.add(stat);
                } catch(Exception e) {
                    System.out.println("Unable to read file " + path.toString());
                }
            }
        });
        return stats;
    }

    public static StatisticsHolder collectFromFile(Path path) throws Exception {
        return collectFromFile(path, m -> true);
    }

    /**
     * Collects information about the class stored in .java file.
     * @param path path to the .java file.
     * @param filter returns true if you want to take the method into account.
     * @return statistics about the class that is stored in that file.
     */
    public static StatisticsHolder collectFromFile(Path path, Predicate<MethodDeclaration> filter) throws Exception {
        StatisticsHolder stats = new StatisticsHolder();
        collectFromFile(path, stats, filter);
        return stats;
    }

    public static void collectFromFile(Path path, StatisticsHolder stats, Predicate<MethodDeclaration> filter)
            throws Exception {
        FileInputStream in = new FileInputStream(path.toString());
        int content;
        StringBuilder codeBuilder = new StringBuilder();
        while ((content = in.read()) != -1) {
            // convert to char and display it
            codeBuilder.append((char) content);
        }
        String code = codeBuilder.toString();
        CompilationUnit cu = JavaParser.parse(code);
        CompilationUnitCollector.getStatistics(cu, stats, filter);
        LayoutCollector.getLayoutFeatures(code, stats);
    }
}
