package javaspring.carservice.service;

import jakarta.validation.Valid;
import javaspring.carservice.dto.CreateMaintenanceDto;
import javaspring.carservice.dto.MaintenanceDto;
import javaspring.carservice.entity.Maintenance;
import javaspring.carservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public List<MaintenanceDto> getAllMaintenances() {
        return maintenanceRepository.findAll().stream()
                .map(maintenance -> new MaintenanceDto(
                        maintenance.getId().toHexString(),
                        maintenance.getName(),
                        maintenance.getDescription(),
                        maintenance.getPrice(),
                        maintenance.getCreatedAt()
                ))
                .toList();
    }

    public MaintenanceDto createMaintenance(@Valid CreateMaintenanceDto maintenanceDto) {
        Maintenance maintenance = Maintenance.builder()
                .id(new ObjectId())
                .name(maintenanceDto.name())
                .description(maintenanceDto.description())
                .price(maintenanceDto.price())
                .createdAt(LocalDateTime.now())
                .build();

        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);

        return new MaintenanceDto(
                savedMaintenance.getId().toHexString(),
                savedMaintenance.getName(),
                savedMaintenance.getDescription(),
                savedMaintenance.getPrice(),
                savedMaintenance.getCreatedAt()
        );
    }

    public void deleteMaintenance(String id) {
        ObjectId objectId = new ObjectId(id);
        if (!maintenanceRepository.existsById(objectId)) {
            throw new IllegalArgumentException("Maintenance record with id " + id + " not found.");
        }
        maintenanceRepository.deleteById(objectId);
    }
}
