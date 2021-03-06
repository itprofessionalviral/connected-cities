package dev.viralkumar.mcard.connectedcities;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ConnectedCitiesApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void connectedCities() throws Exception {
    this.mockMvc.perform(get("/connected")
        .param("origin", "Boston")
        .param("destination", "Newark"))
        .andExpect(status().isOk())
        .andExpect(content().string("yes"));
  }

  @Test
  void notConnectedCities() throws Exception {
    this.mockMvc.perform(get("/connected")
        .param("origin", "Boston")
        .param("destination", "Albany"))
        .andExpect(status().isOk())
        .andExpect(content().string("no"));
  }

}
