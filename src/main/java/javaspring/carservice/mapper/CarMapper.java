package javaspring.carservice.mapper;


import javaspring.carservice.entity.Car;
import javaspring.carservice.api.dto.CarDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto mapToDto(Car car);

    Car mapToEntity(CarDto dto);

    Car updateEntity(@MappingTarget Car entity, CarDto updateWith);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car updateEntityPartially(@MappingTarget Car entity, CarDto updateWith);


}
