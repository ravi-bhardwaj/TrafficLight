package com.demo.trafficlight;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.state.TrafficLightState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
class TrafficLightApplicationTests {

    @Autowired
    private TrafficLightApplication trafficLightApplication;
    @Autowired
    private StateMachine<TrafficLightState, TrafficLightEvent> stateMachine;

    @Test
    void contextLoads() {
    }

    @Test
    void testRun() throws Exception {
        assumeTrue(trafficLightApplication.trafficLightTimer != null);
        trafficLightApplication.run();
        assertSame(stateMachine.getInitialState().getId(), TrafficLightState.GREEN);
    }

}
