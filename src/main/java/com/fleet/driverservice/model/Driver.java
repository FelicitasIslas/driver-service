
package com.fleet.driverservice.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    private String id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String licenseCategory;

    @Column(nullable = false)
    private LocalDate licenseExpirationDate;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal safetyScore;

    private String vehicleId;
    private String assignmentId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "driver_certifications",
        joinColumns = @JoinColumn(name = "driver_id")
    )
    @Column(name = "certification")
    private Set<String> certifications = new HashSet<>();

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }
    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public LocalDate getLicenseExpirationDate() {
        return licenseExpirationDate;
    }
    public void setLicenseExpirationDate(LocalDate date) {
        this.licenseExpirationDate = date;
    }

    public BigDecimal getSafetyScore() {
        return safetyScore;
    }
    public void setSafetyScore(BigDecimal score) {
        this.safetyScore = score;
    }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String id) {
        this.vehicleId = id;
    }

    public String getAssignmentId() {
        return assignmentId;
    }
    public void setAssignmentId(String id) {
        this.assignmentId = id;
    }

    public Set<String> getCertifications() {
        return certifications;
    }
    public void setCertifications(Set<String> certifications) {
        this.certifications = certifications;
    }
}
