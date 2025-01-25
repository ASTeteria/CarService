package javaspring.carservice.controller;


import jakarta.validation.Valid;
import javaspring.carservice.dto.CarDto;
import javaspring.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @Secured({"USER", "ADMIN"})
    @PostMapping
    public CarDto createCar(@RequestBody @Valid CarDto carDto) {
        return carService.createCar(carDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return ResponseEntity.of(carService.getCarById(id));
    }

    @GetMapping
    public List<CarDto> getCars(
            @RequestParam(name = "minEnginePower", required = false) Integer minEnginePower,
            @RequestParam(name = "maxEnginePower", required = false) Integer maxEnginePower
    ) {
        return carService.getCarsByEnginePowerRange(minEnginePower, maxEnginePower);
    }

    @Secured({"USER", "ADMIN"})
    @PutMapping("/{id}")
    public CarDto updateCar(@PathVariable Long id, @RequestBody @Valid CarDto carDto) {
        return carService.updateCar(id, carDto);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
