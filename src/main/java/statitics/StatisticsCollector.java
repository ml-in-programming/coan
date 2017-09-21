package statitics;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StatisticsCollector {

    /**
     * Collects information about the class stored in .java file.
     * @param path path to the .java file.
     * @return statistics about the class that is stored in that file.
     * @throws FileNotFoundException if there was no file at given path.
     */
    public static StatisticsHolder collectFromFile(String path) throws FileNotFoundException {
        StatisticsHolder stats = new StatisticsHolder();

        // Parse the file
        FileInputStream in = new FileInputStream(path);
        CompilationUnit cu = JavaParser.parse(in);
        AstCollector.printAst(cu);

        MethodCollector.getStatistics(cu, stats);
        return stats;
    }
}
