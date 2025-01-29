package javaspring.carservice.service;

import javaspring.carservice.api.event.dto.CarDeletedPayload;
import javaspring.carservice.api.event.producer.ICarEventsProducer;
import javaspring.carservice.entity.Car;

import javaspring.carservice.mapper.CarMapper;
import javaspring.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import javaspring.carservice.api.dto.CarDto;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final ICarEventsProducer carEventsProducer;


//    public List<CarDto> getCarsByEnginePowerRange(Integer minEnginePower, Integer maxEnginePower) {
//        List<Car> cars;
//        if (minEnginePower != null && maxEnginePower != null) {
//            cars = carRepository.findByEnginePowerBetween(minEnginePower, maxEnginePower);
//        } else if (minEnginePower != null) {
//            cars = carRepository.findByEnginePowerBetween(minEnginePower, Integer.MAX_VALUE);
//        } else if (maxEnginePower != null) {
//            cars = carRepository.findByEnginePowerBetween(0, maxEnginePower);
//        } else {
//            cars = carRepository.findAll();
//        }
//        return cars.stream().map(carMapper::mapToDto).toList();
//    }

//    public Optional<CarDto> getCarById(Long id) {
//        return carRepository.findById(id).map(carMapper::mapToDto);
//    }

//    public CarDto createCar(CarDto createCarDto) {
//        Car car = Car.builder()
//                .model(createCarDto.model())
//                .enginePower(createCarDto.enginePower())
//                .torque(createCarDto.torque())
//                .build();
//        Car savedCar = carRepository.save(car);
//        return carMapper.toDto(savedCar);
//    }


//    public CarDto updateCar(Long id, CarDto carDto) {
//        Car car = carRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Car with id " + id + " not found"));
//        car.setModel(carDto.model());
//        car.setEnginePower(carDto.enginePower());
//        car.setTorque(carDto.torque());
//        car.setLastMaintenanceTimestamp(carDto.lastMaintenanceTimestamp());
//        Car updatedCar = carRepository.save(car);
//        return carMapper.toDto(updatedCar);
//    }

    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new IllegalArgumentException("Car with id " + id + " not found");
        }
        carRepository.deleteById(id);
        carEventsProducer.carDeleted(new CarDeletedPayload()
                .withCarId(id));


    }

    public CarDto createCar(CarDto carDto) {
        Car car = carMapper.mapToEntity(carDto);
        car = carRepository.save(car);
        return carMapper.mapToDto(car);
    }


    public Optional<CarDto> findByid(Long id) {
        return carRepository.findById(id)
                .map(carMapper::mapToDto);

    }

    public List<CarDto> findAllByEnginePowerBetween(Integer minEnginePower, Integer maxEnginePower) {
        return carRepository.findAllByEnginePowerBetween(minEnginePower, maxEnginePower)
                .stream()
                .map(carMapper::mapToDto)
                .toList();

    }

    public List<CarDto> findAllByEnginePowerGreaterThan(Integer minEnginePower) {
        return carRepository.findAllByEnginePowerGreaterThan(minEnginePower).stream()
                .map(carMapper::mapToDto)
                .toList();
    }
    public List<CarDto> findAllByEnginePowerLessThan(Integer maxEnginePower) {
        return carRepository.findAllByEnginePowerGreaterThan(maxEnginePower).stream()
                .map(carMapper::mapToDto)
                .toList();
    }
    public List<CarDto> findAll() {
        return carRepository.findAll().stream()
                .map(carMapper::mapToDto)
                .toList();
    }
    @Transactional
    public Optional<CarDto> update(Long id, CarDto updateWith) {
        return carRepository.findById(id)
                .map(car -> carMapper.updateEntity(car, updateWith))
                .map(carMapper::mapToDto);

    }

    @Transactional
    public Optional<CarDto> updatePartially(Long id, CarDto updateWith) {
        return carRepository.findById(id)
                .map(car -> carMapper.updateEntityPartially(car, updateWith))
                .map(carMapper::mapToDto);

    }
}
