
package com.fleet.driverservice.service;

import com.fleet.driverservice.dto.DriverRequest;
import com.fleet.driverservice.exception.ResourceNotFoundException;
import com.fleet.driverservice.model.Driver;
import com.fleet.driverservice.repository.DriverRepository;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DriverService {

    private final DriverRepository repository;

    public DriverService(DriverRepository repository) {
        this.repository = repository;
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public Driver findById(String id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Conductor no encontrado: " + id));
    }

    public Driver findByVehicle(String vehicleId) {
        return repository.findByVehicleId(vehicleId)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Sin conductor asignado: " + vehicleId));
    }

    public Driver create(DriverRequest request) {
        Driver driver = new Driver();
        driver.setId("drv-" +
            UUID.randomUUID().toString().substring(0, 8));
        apply(driver, request);
        return repository.save(driver);
    }

    public Driver update(String id, DriverRequest request) {
        Driver driver = findById(id);
        apply(driver, request);
        return repository.save(driver);
    }

    public void delete(String id) {
        Driver driver = findById(id);
        repository.delete(driver);
    }

    private void apply(Driver driver, DriverRequest request) {
        driver.setFullName(request.fullName());
        driver.setLicenseCategory(request.licenseCategory());
        driver.setLicenseExpirationDate(
            request.licenseExpirationDate());
        driver.setSafetyScore(request.safetyScore());
        driver.setVehicleId(request.vehicleId());
        driver.setAssignmentId(request.assignmentId());
        driver.setCertifications(
            request.certifications() == null
                ? new HashSet<>()
                : new HashSet<>(request.certifications())
        );
    }
}
