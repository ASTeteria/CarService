package javaspring.carservice.service;

import javaspring.carservice.dto.CarDto;
import javaspring.carservice.entity.Car;
import javaspring.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    /**
     * Отримує всі автомобілі.
     *
     * @return Список CarDto
     */
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getModel(),
                        car.getEnginePower(),
                        car.getTorque(),
                        car.getLastMaintenanceTimestamp()
                ))
                .toList();
    }

    /**
     * Створює новий автомобіль.
     *
     * @param carDto DTO для створення автомобіля
     * @return CarDto збереженого автомобіля
     */
    public CarDto createCar(CarDto carDto) {
        Car car = new Car();
        car.setModel(carDto.model());
        car.setEnginePower(carDto.enginePower());
        car.setTorque(carDto.torque());
        car.setLastMaintenanceTimestamp(carDto.lastMaintenanceTimestamp());
        Car savedCar = carRepository.save(car);
        return new CarDto(
                savedCar.getId(),
                savedCar.getModel(),
                savedCar.getEnginePower(),
                savedCar.getTorque(),
                savedCar.getLastMaintenanceTimestamp()
        );
    }

    /**
     * Оновлює автомобіль за ID.
     *
     * @param id     ID автомобіля
     * @param carDto DTO для оновлення
     * @return Оновлений CarDto
     */
    public CarDto updateCar(Long id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car with id " + id + " not found"));
        car.setModel(carDto.model());
        car.setEnginePower(carDto.enginePower());
        car.setTorque(carDto.torque());
        car.setLastMaintenanceTimestamp(carDto.lastMaintenanceTimestamp());
        Car updatedCar = carRepository.save(car);
        return new CarDto(
                updatedCar.getId(),
                updatedCar.getModel(),
                updatedCar.getEnginePower(),
                updatedCar.getTorque(),
                updatedCar.getLastMaintenanceTimestamp()
        );
    }

    /**
     * Видаляє автомобіль за ID.
     *
     * @param id ID автомобіля
     */
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new IllegalArgumentException("Car with id " + id + " not found");
        }
        carRepository.deleteById(id);
    }

    /**
     * Повертає автомобілі в діапазоні потужності.
     *
     * @param minPower Мінімальна потужність
     * @param maxPower Максимальна потужність
     * @return Список CarDto
     */
    public List<CarDto> findCarsByEnginePowerRange(int minPower, int maxPower) {
        return carRepository.findByEnginePowerBetween(minPower, maxPower).stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getModel(),
                        car.getEnginePower(),
                        car.getTorque(),
                        car.getLastMaintenanceTimestamp()
                ))
                .toList();
    }
}
