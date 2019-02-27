package com.victoroliveira.navigation.resources;

import com.victoroliveira.navigation.domain.City;
import com.victoroliveira.navigation.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class CityResource {

    @Autowired
    private CityService service;

    @RequestMapping(value = "/capitals", method = RequestMethod.GET)
    private ResponseEntity<?> findCapitals() {
        return ResponseEntity.ok().body(service.findCapitals());
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    private ResponseEntity<?> count() {
        Map<String, Integer> count = new HashMap<>();
        count.put("Registros", service.count());
        return ResponseEntity.ok().body(count);
    }

    @RequestMapping(value = "/{ibgeId}", method = RequestMethod.GET)
    private ResponseEntity<?> findIbge(@PathVariable Integer ibgeId) {
        City obj = service.findCity(ibgeId);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<?> findUF(@RequestParam(value = "uf") String uf) {
        List<City> citiesUF = service.findUF(uf);
        return ResponseEntity.ok().body(citiesUF);
    }

    @RequestMapping(value = "/{ibgeId}", method = RequestMethod.DELETE)
    private ResponseEntity<?> delete(@PathVariable Integer ibgeId) {
        service.deleteCity(ibgeId);
        return ResponseEntity.ok().body("deleted successfully");
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<?> createCity(@RequestBody City obj) {
        service.saveCity(obj);
        return ResponseEntity.ok().body("City registered");
    }


}
