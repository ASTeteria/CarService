package javaspring.carservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateCarDto(
        @NotBlank(message = "Model cannot be blank")
        String model,

        @Positive(message = "Engine power must be greater than 0")
        int enginePower,

        @Positive(message = "Torque must be greater than 0")
        int torque
) {}

