package javaspring.carservice.mapper;



import javaspring.carservice.dto.CreateMaintenanceDto;
import javaspring.carservice.dto.MaintenanceDto;
import javaspring.carservice.entity.Maintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface MaintenanceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Maintenance mapToMaintenance(CreateMaintenanceDto createMaintenanceDto);

    @Mapping(target = "id", expression = "java(maintenance.getId().toHexString())")
    MaintenanceDto mapToDto(Maintenance maintenance);
}

