package javaspring.carservice.dto;


import java.time.LocalDateTime;

public record MaintenanceDto(
        String id,
        String name,
        String description,
        Double price,
        LocalDateTime createdAt
) {}




