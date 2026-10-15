
package com.fleet.driverservice.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record DriverRequest(
    @NotBlank String fullName,
    @NotBlank String licenseCategory,
    @NotNull LocalDate licenseExpirationDate,
    @NotNull @DecimalMin("0.0") @DecimalMax("100.0")
    BigDecimal safetyScore,
    String vehicleId,
    String assignmentId,
    Set<@NotBlank String> certifications
) {}
