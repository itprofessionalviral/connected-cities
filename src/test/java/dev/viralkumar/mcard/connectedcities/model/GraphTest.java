package dev.viralkumar.mcard.connectedcities.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphTest {

    private static final Graph<String> citiesGraph = new Graph<>();

    @BeforeAll
    static void beforeAll() {
        assertThat(citiesGraph.addEdge("Boston", "New York")).isTrue();
        assertThat(citiesGraph.addEdge("Philadelphia", "Newark")).isTrue();
        assertThat(citiesGraph.addEdge("Newark", "Boston")).isTrue();
        assertThat(citiesGraph.addEdge("Trenton", "Albany")).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "Boston, Newark, True",
            "Boston, Philadelphia, True",
            "Philadelphia, Albany, False",
            "Boston, Jersey City, False",
            "Jersey City, Newark, False"
    })
    void isConnected(String cityFrom, String cityTo, boolean expected) {
        assertThat(citiesGraph.isConnected(cityFrom, cityTo))
                .as(String.format("%s -> %s : %s", cityFrom, cityTo, expected)).isEqualTo(expected);
    }

    @Test
    void addEdge() {
        Graph<String> graph = new Graph<>();
        assertThat(graph.addEdge("Boston", "New York")).isTrue();
        assertThat(graph.addEdge("Boston", "Chicago")).isTrue();
        assertThat(graph.addEdge("Boston", "Chicago")).isFalse();
    }

    @Test
    void isConnectedWithNullInput() {
        Graph<String> graph = new Graph<>();
        assertThrows(NullPointerException.class, () -> graph.isConnected("Boston", null),
                "Input must not be null");
        assertThrows(NullPointerException.class, () -> graph.isConnected(null, "Boston"),
                "Input must not be null");
    }
}