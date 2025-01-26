package javaspring.carservice.event;

import lombok.Builder;

@Builder
public record CarDeletedEvent(Long carId) {
}