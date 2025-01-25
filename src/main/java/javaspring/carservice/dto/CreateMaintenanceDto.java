package javaspring.carservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateMaintenanceDto(
        @NotBlank String name,
        String description,
        @Positive Double price
) {}



