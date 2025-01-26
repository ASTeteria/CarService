package javaspring.carservice.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarEventProducer {

    private final KafkaTemplate<Integer, CarDeletedEvent> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    public void produceCarDeletedEvent(CarDeletedEvent event) {
        log.info("Producing event to '{}' with payload {}", topic, event);
        kafkaTemplate.send(topic, event);
    }
}
