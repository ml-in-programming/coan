package statitics;

import org.apache.commons.lang3.StringUtils;

public class LayoutCollector {

    public static void getLayoutFeatures(String file, StatisticsHolder stats) {
        String[] lines = file.split("\n");
        stats.totalLength += file.length();
        stats.spaces += StringUtils.countMatches(file, " ");
        stats.tabs += StringUtils.countMatches(file, "\t");
        stats.lines += lines.length;
        stats.tabsLeadLines =
                StringUtils.countMatches(file, "\n\t") > StringUtils.countMatches(file, "\n ");
        int newLine = StringUtils.countMatches(file, "\n{");
        int space = StringUtils.countMatches(file, " {");
        int other = StringUtils.countMatches(file, "{") - newLine - space;
        if (newLine >= space && newLine >= other) {
            stats.punctuationBeforeBrace = "NewLine";
        } else if (space >= newLine && space >= other) {
            stats.punctuationBeforeBrace = "Space";
        } else {
            stats.punctuationBeforeBrace = "Other";
        }
        for (String line : lines) {
            if (line.trim().length() == 0) {
                stats.emptyLines++;
            }
        }
        if (file.charAt(file.length() - 1) == '\n') {
            stats.emptyLines++;
        }
        stats.whitespaceCharacters = stats.tabs + stats.spaces + stats.lines - 1;
        String[] words = file.split("\\W+");
    }
}
