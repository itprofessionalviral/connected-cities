package dev.viralkumar.mcard.connectedcities.service;

import dev.viralkumar.mcard.connectedcities.model.Graph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CityRouteService {

  private final Graph<String> citiesGraph;

  public CityRouteService(@Value("${city.routes.resource.path:classpath:city.txt}")
      Resource routesResource) {
    citiesGraph = new Graph<>();
    new CsvResourceGraphLoader().loadRoutes(routesResource, citiesGraph::addEdge);
    log.debug("citiesGraph: {}", citiesGraph);
  }

  public boolean isConnected(String cityFrom, String cityTo) {
    return citiesGraph.isConnected(cityFrom, cityTo);
  }
}
