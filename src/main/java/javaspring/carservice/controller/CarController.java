package javaspring.carservice.controller;


import javaspring.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.javaspring.carservice.api.controller.CarsApi;
import org.javaspring.carservice.api.dto.CarDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController implements CarsApi {

    private final CarService carService;


//    @Secured({"USER", "ADMIN"})
//    @PostMapping
//    public CarDto createCar(@RequestBody @Valid CreateCarDto createCarDto) {
//        return carService.createCar(createCarDto);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
//        return ResponseEntity.of(carService.getCarById(id));
//    }

//    @GetMapping
//    public List<CarDto> getCars(
//            @RequestParam(name = "minEnginePower", required = false) Integer minEnginePower,
//            @RequestParam(name = "maxEnginePower", required = false) Integer maxEnginePower
//    ) {
//        return carService.getCarsByEnginePowerRange(minEnginePower, maxEnginePower);
//    }

//    @Secured({"USER", "ADMIN"})
//    @PutMapping("/{id}")
//    public CarDto updateCar(@PathVariable Long id, @RequestBody @Valid CarDto carDto) {
//        return carService.updateCar(id, carDto);
//    }
//
    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
    @Secured({"USER", "ADMIN"})
    @Override
    public ResponseEntity<CarDto> createCar(CarDto carDto) {
        return ResponseEntity.ok(carService.createCar(carDto));
    }

    @Override
    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
        return ResponseEntity.of(carService.findByid(id));
    }

    @Override
    public ResponseEntity<List<CarDto>> getCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @Override
    public ResponseEntity<CarDto> modifyCar(Long id, CarDto carDto) {
        return ResponseEntity.of(carService.update(id, carDto));
    }
}
//    @Override
//    public ResponseEntity<List<CarDto>> getCars(Integer minEnginePower, Integer maxEnginePower) {
//        if (minEnginePower != null && maxEnginePower != null) {
//            return ResponseEntity.ok(carService.findAllByEnginePowerBetween(minEnginePower, maxEnginePower));
//        } else if (minEnginePower != null) {
//            return ResponseEntity.ok(carService.findAllByEnginePowerGreaterThan(minEnginePower));
//        } else if (maxEnginePower != null) {
//            return ResponseEntity.ok(carService.findAllByEnginePowerLessThan(maxEnginePower));
//        } else {
//            return ResponseEntity.ok(carService.findAll());
//        }


//    @Override
//    public ResponseEntity<CarDto> modifyCar(Long id, CarDto carDto) {
//        return ResponseEntity.of(carService.update(id, carDto));
//    }




