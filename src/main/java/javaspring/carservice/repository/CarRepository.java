package javaspring.carservice.repository;

import javaspring.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByEnginePowerBetween(int minPower, int maxPower);
}
