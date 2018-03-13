package statitics;

import static statitics.StatisticsHolder.*;

import org.apache.commons.lang3.StringUtils;

public class LayoutCollector {

    public static void getLayoutFeatures(String file, StatisticsHolder stats) {
        String[] lines = file.split("\n");
        stats.addToIntField(TOTAL_LENGTH, file.length());
        stats.addToIntField(SPACES, StringUtils.countMatches(file, " "));
        stats.addToIntField(TABS, StringUtils.countMatches(file, "\t"));
        stats.addToIntField(LINES, lines.length);
        boolean tabsLeadLines = StringUtils.countMatches(file, "\n\t") > StringUtils.countMatches(file, "\n ");
        if (tabsLeadLines) {
            stats.setNominalField(TABS_LEAD_LINES, "Tabs");
        } else {
            stats.setNominalField(TABS_LEAD_LINES, "Spaces");
        }
        int newLine = StringUtils.countMatches(file, "\n{");
        int space = StringUtils.countMatches(file, " {");
        int other = StringUtils.countMatches(file, "{") - newLine - space;
        if (newLine >= space && newLine >= other) {
            stats.setNominalField(PUNCTUATION_BEFORE_BRACE, "NewLine");
        } else if (space >= newLine && space >= other) {
            stats.setNominalField(PUNCTUATION_BEFORE_BRACE, "Space");
        } else {
            stats.setNominalField(PUNCTUATION_BEFORE_BRACE, "Other");
        }
        for (String line : lines) {
            if (line.trim().length() == 0) {
                stats.addToIntField(EMPTY_LINES, 1);
            }
        }
        if (file.charAt(file.length() - 1) == '\n') {
            stats.addToIntField(EMPTY_LINES, 1);
        }
        stats.addToIntField(WHITESPACE_CHARS,
                stats.getIntField(TABS) + stats.getIntField(SPACES) + (stats.getIntField(LINES) - 1));
        String[] words = file.split("\\W+");
    }
}
