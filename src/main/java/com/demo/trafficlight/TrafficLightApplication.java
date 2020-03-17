package com.demo.trafficlight;

import com.demo.trafficlight.component.timer.TrafficLightTimer;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class TrafficLightApplication implements CommandLineRunner {

    TrafficLightTimer trafficLightTimer;

    public static void main(String[] args) {
        SpringApplication.run(TrafficLightApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        trafficLightTimer.start();
    }
}
