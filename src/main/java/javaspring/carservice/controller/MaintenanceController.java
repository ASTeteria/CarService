package javaspring.carservice.controller;

import jakarta.validation.Valid;
import javaspring.carservice.dto.CreateMaintenanceDto;
import javaspring.carservice.dto.MaintenanceDto;
import javaspring.carservice.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Secured("ADMIN")
    @PostMapping
    public MaintenanceDto createMaintenance(@RequestBody @Valid CreateMaintenanceDto createMaintenanceDto) {
        return maintenanceService.createMaintenance(createMaintenanceDto);
    }

    @Secured("ADMIN")
    @GetMapping
    public List<MaintenanceDto> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable String id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
