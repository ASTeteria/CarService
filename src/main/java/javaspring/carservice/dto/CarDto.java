package javaspring.carservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CarDto(
        Long id,
        @NotBlank String model,
        @Positive int enginePower,
        @Positive int torque,
        LocalDateTime lastMaintenanceTimestamp
) {}
