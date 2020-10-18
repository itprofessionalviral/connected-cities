package dev.viralkumar.mcard.connectedcities.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

class CsvResourceGraphLoaderTest {

  @Test
  void loadRoutes() {
    CsvResourceGraphLoader routeLoader = new CsvResourceGraphLoader();
    AtomicLong routeCount = new AtomicLong();
    routeLoader.loadRoutes(new ClassPathResource("test-cities.txt"),
        (from, to) -> routeCount.getAndIncrement());
    Assertions.assertThat(routeCount.get()).isEqualTo(4);
  }

  @Test
  void loadRoutesMissingFile() {
    CsvResourceGraphLoader routeLoader = new CsvResourceGraphLoader();
    assertThrows(IllegalArgumentException.class,
        () -> routeLoader.loadRoutes(new ClassPathResource("small_cities.txt"), (from, to) -> {
        }),
        "File is missing");
  }

  @Test
  void loadRoutesFromInputStream() throws IOException {
    CsvResourceGraphLoader routeLoader = new CsvResourceGraphLoader();
    AtomicLong routeCount = new AtomicLong();
    Resource resource = new InputStreamResource(
        new ClassPathResource("test-cities.txt").getInputStream());
    routeLoader.loadRoutes(resource, (from, to) -> routeCount.getAndIncrement());
    Assertions.assertThat(routeCount.get()).isEqualTo(4);
  }

}