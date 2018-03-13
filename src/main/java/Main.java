import com.sun.javaws.exceptions.InvalidArgumentException;
import statitics.StatisticsCollector;
import statitics.StatisticsHolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Collects stats from all .java files in repositories saved as subdirectories in a directory.
 * Repositories with less than MIN_FILES_TO_TEST files are ignored.
 * Results are saved in .csv format.
 */
public class Main {

    private static final String PROJECT = "github-java-corpus-sample";
    private static final String ROOT = "/home/egor/Work/testData/";

    /**
     * Path to file where .csv with stats should be saved.
     */
    private static final String PATH_TO_CSV = ROOT + "data.csv";
    /**
     * Directory with repositories.
     */
    private static final String PATH_TO_REPOSITORIES = ROOT + "data/" + PROJECT + "/reps/";
    /**
     * Projects with less files will be ignored.
     */
    private static final int MIN_FILES_TO_TEST = 1;

    private static void writeCsvHeader(PrintWriter writer, List<String> features) {
        writer.println(features.stream().collect(Collectors.joining(",", "", "")));
    }

    private static void writeFileStats(PrintWriter writer, StatisticsHolder stats, List<String> features) {
        writer.println(features.stream().map(feature -> {
            StatisticsHolder.ValueType type = StatisticsHolder.getType(feature);
            if (type == null) {
                throw new IllegalArgumentException("Feature " + feature + " doesn't exist.");
            }
            switch (type) {
                case INT:
                    return Integer.toString(stats.getIntField(feature));
                case STRING:
                    return stats.getStringField(feature);
                case NOMINAL:
                    return stats.getNominalField(feature);
                default:
                    return "NaN";
            }
        }).collect(Collectors.joining(",", "", "")));
    }

    private static void writeStats(PrintWriter writer, File[] subDirs, List<String> features) throws IOException {
        for (File subDir : subDirs) {
            if (subDir.isDirectory()) {
                System.out.println("Collecting from: " + subDir.getName());
                List<StatisticsHolder> allStats = StatisticsCollector.collectFromProject(subDir.toPath());
                if (allStats.size() < MIN_FILES_TO_TEST) {
                    continue;
                }
                for (StatisticsHolder stats : allStats) {
                    writeFileStats(writer, stats, features);
                }
            }
        }
    }

    private static List<String> getFeatures() {
        List<String> features = new ArrayList<>(StatisticsHolder.getListOfFeatures());
        features.remove(StatisticsHolder.AST);
        return features;
    }

    private static void processAndSaveData() throws Exception {
        File dataFile = new File(PATH_TO_CSV);
        File repsDirectory = new File(PATH_TO_REPOSITORIES);
        File[] subDirs = repsDirectory.listFiles();

        PrintWriter writer = new PrintWriter(dataFile, "UTF-8");
        List<String> features = getFeatures();
        writeCsvHeader(writer, features);
        writeStats(writer, subDirs, features);
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        processAndSaveData();
    }
}
