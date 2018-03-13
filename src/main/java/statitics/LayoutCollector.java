package statitics;

import static statitics.StatisticsHolder.*;

import org.apache.commons.lang3.StringUtils;

public class LayoutCollector {

    public static void getLayoutFeatures(String file, StatisticsHolder stats) {
        String[] lines = file.split("\n");
        stats.addToIntFeature(TOTAL_LENGTH, file.length());
        stats.addToIntFeature(SPACES, StringUtils.countMatches(file, " "));
        stats.addToIntFeature(TABS, StringUtils.countMatches(file, "\t"));
        stats.addToIntFeature(LINES, lines.length);
        boolean tabsLeadLines = StringUtils.countMatches(file, "\n\t") > StringUtils.countMatches(file, "\n ");
        if (tabsLeadLines) {
            stats.setNominalFeature(TABS_LEAD_LINES, "Tabs");
        } else {
            stats.setNominalFeature(TABS_LEAD_LINES, "Spaces");
        }
        int newLine = StringUtils.countMatches(file, "\n{");
        int space = StringUtils.countMatches(file, " {");
        int other = StringUtils.countMatches(file, "{") - newLine - space;
        if (newLine >= space && newLine >= other) {
            stats.setNominalFeature(PUNCTUATION_BEFORE_BRACE, "NewLine");
        } else if (space >= newLine && space >= other) {
            stats.setNominalFeature(PUNCTUATION_BEFORE_BRACE, "Space");
        } else {
            stats.setNominalFeature(PUNCTUATION_BEFORE_BRACE, "Other");
        }
        for (String line : lines) {
            if (line.trim().length() == 0) {
                stats.addToIntFeature(EMPTY_LINES, 1);
            }
        }
        if (file.charAt(file.length() - 1) == '\n') {
            stats.addToIntFeature(EMPTY_LINES, 1);
        }
        stats.addToIntFeature(WHITESPACE_CHARS,
                stats.getIntFeature(TABS) + stats.getIntFeature(SPACES) + (stats.getIntFeature(LINES) - 1));
        String[] words = file.split("\\W+");
    }
}
