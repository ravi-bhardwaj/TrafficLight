package com.demo.trafficlight.service;

import com.demo.trafficlight.domain.TrafficLight;
import com.demo.trafficlight.domain.state.TrafficLightState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TrafficLightServiceTest {

    @Autowired
    private TrafficLightService trafficLightService;

    @Test
    void testChangeState() {
        TrafficLight trafficLight = new TrafficLight();
        trafficLightService.changeState(trafficLight);
        assertEquals(TrafficLightState.AMBER, trafficLight.getTrafficLightState());
    }
}