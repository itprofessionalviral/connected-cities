package dev.viralkumar.mcard.connectedcities.model;

import lombok.NonNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Undirected Graph implementation
 *
 * @param <T> type of element in Graph
 */
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

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (T v : getAdjVertices(vertex)) {
                if (v.equals(to)) {
                    return true;
                }
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        return false;
    }
}
