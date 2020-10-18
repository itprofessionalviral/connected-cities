package dev.viralkumar.mcard.connectedcities.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Undirected Graph implementation
 *
 * @param <T> type of element in Graph
 */
@Slf4j
public class Graph<T> {

  private final ConcurrentMap<T, Set<T>> adjVertices = new ConcurrentHashMap<>();

  /**
   * Add an edge between vertex
   *
   * @param source source vertex
   * @param target target vertex
   * @return true if edge added or else false
   */
  public boolean addEdge(T source, T target) {
    final boolean added = addVertex(source).add(target);
    addVertex(target).add(source);
    return added;
  }

  private Set<T> addVertex(T value) {
    return adjVertices.computeIfAbsent(value, key -> ConcurrentHashMap.newKeySet());
  }

  public Set<T> getAdjVertices(T value) {
    final Set<T> adjacent = adjVertices.get(value);
    return adjacent == null ? Collections.emptySet() : adjacent;
  }

  public boolean isConnected(@NonNull T from, @NonNull T to) {
    if (getAdjVertices(to).isEmpty()) {
      return false;
    }
    Set<T> visited = new HashSet<>();
    Queue<T> queue = new LinkedList<>();

    visited.add(from);
    queue.add(from);

    log.trace("isConnected. from: {} to: {}", from, to);
    while (!queue.isEmpty()) {
      T vertex = queue.poll();
      log.trace("peek: {}", vertex);
      for (T v : getAdjVertices(vertex)) {
        if (v.equals(to)) {
          return true;
        }
        if (!visited.contains(v)) {
          log.trace("  traversal: {}", v);
          visited.add(v);
          queue.add(v);
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    final StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");
    adjVertices.forEach((key, values) -> stringJoiner
        .add(String.format("\"%s\" : [%s]", key, values.stream()
            .map(v -> "\"" + v + "\"")
            .collect(Collectors.joining(",")))));
    return stringJoiner.toString();
  }
}
