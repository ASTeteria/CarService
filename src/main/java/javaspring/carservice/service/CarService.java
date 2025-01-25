package javaspring.carservice.service;

import javaspring.carservice.dto.CarDto;
import javaspring.carservice.dto.CreateCarDto;
import javaspring.carservice.entity.Car;
import javaspring.carservice.mapper.CarMapper;
import javaspring.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDto> getCarsByEnginePowerRange(Integer minEnginePower, Integer maxEnginePower) {
        List<Car> cars;
        if (minEnginePower != null && maxEnginePower != null) {
            cars = carRepository.findByEnginePowerBetween(minEnginePower, maxEnginePower);
        } else if (minEnginePower != null) {
            cars = carRepository.findByEnginePowerBetween(minEnginePower, Integer.MAX_VALUE);
        } else if (maxEnginePower != null) {
            cars = carRepository.findByEnginePowerBetween(0, maxEnginePower);
        } else {
            cars = carRepository.findAll();
        }
        return cars.stream().map(carMapper::toDto).toList();
    }

    public Optional<CarDto> getCarById(Long id) {
        return carRepository.findById(id).map(carMapper::toDto);
    }

    public CarDto createCar(CreateCarDto createCarDto) {
        Car car = Car.builder()
                .model(createCarDto.model())
                .enginePower(createCarDto.enginePower())
                .torque(createCarDto.torque())
                .build();
        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);
    }


    public CarDto updateCar(Long id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car with id " + id + " not found"));
        car.setModel(carDto.model());
        car.setEnginePower(carDto.enginePower());
        car.setTorque(carDto.torque());
        car.setLastMaintenanceTimestamp(carDto.lastMaintenanceTimestamp());
        Car updatedCar = carRepository.save(car);
        return carMapper.toDto(updatedCar);
    }

    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new IllegalArgumentException("Car with id " + id + " not found");
        }
        carRepository.deleteById(id);
    }
}
