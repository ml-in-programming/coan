import statitics.StatisticsCollector;
import statitics.StatisticsHolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Collects stats from all .java files in repositories saved as subdirectories in a directory.
 * Repositories with less than 3 files are ignored.
 * Results are saved in .csv format.
 */
public class Main {

    private static final String PATH_TO_CSV = "/home/egor/PyCharm2017/projects/simple_lstm/data.csv";
    private static final String PATH_TO_REPOSITORIES = "/home/egor/PyCharm2017/projects/simple_lstm/reps";
    private static final int MIN_FILES_TO_TEST = 3;

    public static void writeHeader(PrintWriter writer) {
        writer.println("ProjectName,Path,For,While,If,Else,ElseIf,TernaryOperator,StringConstant,IntConstant," +
                "CharConstant,Lambda,Break,Continue,Null,LineComment,BlockComment,JavaDocComment,TotalLength,Line," +
                "Tab,Space,EmptyLine,TabsLeadLines,Whitespace,PunctuationBeforeBrace,Method,MethodsCharacters," +
                "MethodsParameters,MethodsLines,Fields,PublicFields,PrivateFields,LocalVariables,FieldsLength," +
                "VariableLength,InnerClasses");
    }

    public static void writeStats(PrintWriter writer, File[] subDirs) throws IOException {
        for (File subDir : subDirs) {
            if (subDir.isDirectory()) {
                List<StatisticsHolder> allStats = StatisticsCollector.collectFromProject(subDir.toPath());
                if (allStats.size() < MIN_FILES_TO_TEST) {
                    continue;
                }
                for (StatisticsHolder stats : allStats) {
                    writer.println(subDir.getName() + "," +
                            stats.path + "," +
                            stats.fors + "," +
                            stats.whiles + "," +
                            stats.ifs + "," +
                            stats.elses + "," +
                            stats.elseIfs + "," +
                            stats.ternary + "," +
                            stats.stringConstants + "," +
                            stats.intConstants + "," +
                            stats.charConstants + "," +
                            stats.lambdas + "," +
                            stats.breaks + "," +
                            stats.continues + "," +
                            stats.nulls + "," +
                            stats.lineComments + "," +
                            stats.blockComments + "," +
                            stats.javaDocComments + "," +
                            stats.totalLength + "," +
                            stats.lines + "," +
                            stats.tabs + "," +
                            stats.spaces + "," +
                            stats.emptyLines + "," +
                            stats.tabsLeadLines + "," +
                            stats.whitespaceCharacters + "," +
                            stats.punctuationBeforeBrace + "," +
                            stats.methods + "," +
                            stats.methodsCharacters + "," +
                            stats.methodsParameters + "," +
                            stats.methodsLines + "," +
                            stats.fields + "," +
                            stats.publicFields + "," +
                            stats.privateFields + "," +
                            stats.localVariables + "," +
                            stats.fieldsLength + "," +
                            stats.variablesLength + "," +
                            stats.innerClasses);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        File dataFile = new File(PATH_TO_CSV);
        File repsDirectory = new File(PATH_TO_REPOSITORIES);
        File[] subDirs = repsDirectory.listFiles();

        PrintWriter writer = new PrintWriter(dataFile, "UTF-8");
        writeHeader(writer);
        writeStats(writer, subDirs);
        writer.close();
    }
}
