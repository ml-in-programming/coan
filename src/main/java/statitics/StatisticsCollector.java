package statitics;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StatisticsCollector {

    /**
     * Recursively collects information about classes stored in .java files in directory and it's subdirectories.
     * @param path path to the root of project.
     * @return cumulative statistics about classes stored in the project.
     */
    public static StatisticsHolder collectFromProject(Path path) throws IOException {
        StatisticsHolder stats = new StatisticsHolder();
        Files.walk(path).forEach(p -> {
            if (p.getFileName().toString().endsWith(".java")) {
                collectFromFile(p, stats);
            }
        });
        return stats;
    }

    /**
     * Collects information about the class stored in .java file.
     * @param path path to the .java file.
     * @return statistics about the class that is stored in that file.
     */
    public static StatisticsHolder collectFromFile(Path path) {
        StatisticsHolder stats = new StatisticsHolder();
        collectFromFile(path, stats);
        return stats;
    }

    public static void collectFromFile(Path path, StatisticsHolder stats) {
        // Parse the file
        try {
            FileInputStream in = new FileInputStream(path.toString());
            CompilationUnit cu = JavaParser.parse(in);
            CompilationUnitCollector.getStatistics(cu, stats);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file " + path.toString());
        }
    }
}
