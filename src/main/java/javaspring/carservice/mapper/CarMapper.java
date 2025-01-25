package javaspring.carservice.mapper;


import javaspring.carservice.dto.CarDto;
import javaspring.carservice.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface CarMapper {

    @Mapping(target = "lastMaintenanceTimestamp", expression = "java(LocalDateTime.now())")
    CarDto toDto(Car car);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastMaintenanceTimestamp", expression = "java(null)")
    Car toEntity(CarDto carDto);
}
