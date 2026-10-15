
package com.fleet.driverservice.controller;

import com.fleet.driverservice.dto.DriverRequest;
import com.fleet.driverservice.model.Driver;
import com.fleet.driverservice.service.DriverService;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping
    public List<Driver> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Driver getById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/by-vehicle/{vehicleId}")
    public Driver getByVehicle(
            @PathVariable String vehicleId) {
        return service.findByVehicle(vehicleId);
    }

    @PostMapping
    public ResponseEntity<Driver> create(
            @Valid @RequestBody DriverRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.create(request));
    }

    @PutMapping("/{id}")
    public Driver update(
            @PathVariable String id,
            @Valid @RequestBody DriverRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
