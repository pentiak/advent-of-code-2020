package com.adventofcode.day7;

import com.adventofcode.utils.InputUtils;
import lombok.extern.log4j.Log4j2;
import org.jgrapht.Graph;
import org.jgrapht.event.TraversalListenerAdapter;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.EdgeReversedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class LuggageProcessor {

  private static final Pattern BAG_REGULATION = Pattern.compile("^([\\w+ ]+) bags contain (.*)$");
  private static final Pattern BAG_CONTAINS = Pattern.compile("(\\d+) ([\\w+ ]+) bags?(, |\\.)");

  private final Graph<String, DefaultWeightedEdge> graph;

  public LuggageProcessor(String inputFile) {
    graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    loadRegulations(inputFile);
  }

  private void loadRegulations(String inputFile) {
    List<String> lines = InputUtils.readInputLines(inputFile);
    for (String line : lines) {
      Matcher matcher = BAG_REGULATION.matcher(line);
      if (matcher.find()) {
        String bag = matcher.group(1);
        String contains = matcher.group(2);
        graph.addVertex(bag);
        if (!"no other bags.".equals(contains)) {
          parseBagContains(bag, contains);
        }
      }
    }
  }

  private void parseBagContains(String bag, String contains) {
    Matcher matcher = BAG_CONTAINS.matcher(contains);
    while (matcher.find()) {
      String insideBag = matcher.group(2);
      graph.addVertex(insideBag);
      graph.setEdgeWeight(graph.addEdge(bag, insideBag), Integer.parseInt(matcher.group(1)));
    }
  }

  public int numberOfBagsContaining(String bag) {
    EdgeReversedGraph<String, DefaultWeightedEdge> reversedGraph = new EdgeReversedGraph<>(graph);

    Set<String> parents = new HashSet<>();
    BreadthFirstIterator<String, DefaultWeightedEdge> breadthFirstIterator = new BreadthFirstIterator<>(reversedGraph,
        bag);
    breadthFirstIterator.next();
    while (breadthFirstIterator.hasNext()) {
      parents.add(breadthFirstIterator.next());
    }

    return parents.size();
  }

  public long numberOfBagsInside(String sourceBag) {
    DepthFirstIterator<String, DefaultWeightedEdge> depthFirstIterator = new DepthFirstIterator<>(graph,
        sourceBag);

    final Map<String, Double> vertexWeights = new HashMap<>();

    depthFirstIterator.addTraversalListener(new TraversalListenerAdapter<>() {

      @Override
      public void vertexFinished(VertexTraversalEvent<String> vertexTraversalEvent) {
        vertexWeights.computeIfAbsent(vertexTraversalEvent.getVertex(), vertex -> {
          Set<DefaultWeightedEdge> edges = graph.outgoingEdgesOf(vertex);
          return 1 + edges.stream().mapToDouble(
              e -> graph.getEdgeWeight(e) * vertexWeights.get(graph.getEdgeTarget(e))).sum();
        });
      }
    });

    while (depthFirstIterator.hasNext()) {
      depthFirstIterator.next();
    }
    return vertexWeights.get(sourceBag).longValue() - 1;
  }
}
