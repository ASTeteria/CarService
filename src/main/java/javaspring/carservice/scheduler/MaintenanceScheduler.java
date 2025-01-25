package javaspring.carservice.scheduler;

import javaspring.carservice.entity.Car;
import javaspring.carservice.repository.CarRepository;
import javaspring.carservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MaintenanceScheduler {

    private final CarRepository carRepository;
    private final EmailService emailService;

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendMaintenanceReminders() {
        List<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            if (car.getLastMaintenanceTimestamp() != null &&
                    car.getLastMaintenanceTimestamp().isBefore(LocalDate.now().minusYears(1).atStartOfDay())) {
                String email = "owner@example.com"; // Замінити на email власника
                String subject = "Maintenance Reminder";
                String text = String.format("Your car %s needs maintenance.", car.getModel());
                emailService.sendEmail(email, subject, text);
            }
        }
    }
}
