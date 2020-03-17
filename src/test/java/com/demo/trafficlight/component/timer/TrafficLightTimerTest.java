package com.demo.trafficlight.component.timer;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.state.TrafficLightState;
import com.demo.trafficlight.repository.TransitionIntervalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.EnumState;
import org.springframework.statemachine.state.State;

import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrafficLightTimerTest {

    @Mock
    private StateMachine<TrafficLightState, TrafficLightEvent> stateMachine;

    @Mock
    private TransitionIntervalRepository transitionIntervalRepository;

    @InjectMocks
    private TrafficLightTimer trafficLightTimer;

    @Test
    void testStart() throws InterruptedException {
        State<TrafficLightState, TrafficLightEvent> state = mock(State.class);
        when(stateMachine.getState()).thenReturn(state);
        when(stateMachine.getState().getId()).thenReturn(TrafficLightState.AMBER);
        trafficLightTimer.start();
        assertNotEquals(TrafficLightState.GREEN, stateMachine.getState().getId());
    }

}