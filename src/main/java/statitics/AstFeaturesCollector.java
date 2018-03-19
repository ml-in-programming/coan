package statitics;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import java.util.*;

import static statitics.StatisticsHolder.*;

public class AstFeaturesCollector {

    private static String getType(Node node) {
        return node.getClass().getName().substring("com.github.javaparser.ast.".length());
    }

    private final Map<Node, Integer> depth = new HashMap<>();
    private final List<Node> nodes = new ArrayList<>();
    private final Map<String, Integer> nodeTypesCount = new HashMap<>();
    private final Map<String, Integer> nodeTypesSumDepth = new HashMap<>();

    private void setDepth(Node node, int d) {
        depth.put(node, d);
        node.getChildNodes().forEach(child -> setDepth(child, d + 1));
    }

    private void collectAllNodes(Node node) {
        nodes.add(node);
        node.getChildNodes().forEach(this::collectAllNodes);
    }

    private void collectNodeInfo(Node node, String type) {
        nodeTypesCount.put(type, nodeTypesCount.getOrDefault(type, 0) + 1);
        nodeTypesSumDepth.put(type, nodeTypesSumDepth.getOrDefault(type, 0) + depth.get(node));
    }

    private void updateStats(StatisticsHolder stats) {
        int maxDepth = depth.values().stream().max(Integer::compareTo).orElse(0);
        int total = nodes.size();
        stats.setIntFeature(AST_MAX_DEPTH, maxDepth);
        stats.setIntFeature(AST_TOTAL, total);
        for (Map.Entry<String, Integer> typeCount : nodeTypesCount.entrySet()) {
            stats.putAstTypeFrequency(typeCount.getKey(), typeCount.getValue() * 1. / total);
        }
        for (Map.Entry<String, Integer> sumDepth : nodeTypesSumDepth.entrySet()) {
            stats.putAstAvgDepth(sumDepth.getKey(), sumDepth.getValue() * 1. / total);
        }
    }

    private void getStats(CompilationUnit compilationUnit, StatisticsHolder stats) {
        collectAllNodes(compilationUnit);
        setDepth(compilationUnit, 0);
        nodes.forEach(node -> collectNodeInfo(node, getType(node)));
        updateStats(stats);
    }

    private Set<String> getTypes(CompilationUnit compilationUnit) {
        collectAllNodes(compilationUnit);
        Set<String> types = new HashSet<>();
        nodes.forEach(node -> types.add(getType(node)));
        return types;
    }

    public static void getStatistics(CompilationUnit compilationUnit, StatisticsHolder stats) {
        new AstFeaturesCollector().getStats(compilationUnit, stats);
    }

    public static Set<String> getNodeTypes(CompilationUnit compilationUnit) {
        return new AstFeaturesCollector().getTypes(compilationUnit);
    }
}