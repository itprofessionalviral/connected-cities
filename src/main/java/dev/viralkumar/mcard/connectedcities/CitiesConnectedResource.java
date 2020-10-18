package dev.viralkumar.mcard.connectedcities;

import dev.viralkumar.mcard.connectedcities.service.CityRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitiesConnectedResource {

    @Autowired
    private CityRouteService cityRouteService;

    @GetMapping("/connected")
    public String connected(@RequestParam(required = false) String origin,
                            @RequestParam(required = false) String destination) {
        return cityRouteService.isConnected(origin, destination) ? "yes" : "no";
    }
}
