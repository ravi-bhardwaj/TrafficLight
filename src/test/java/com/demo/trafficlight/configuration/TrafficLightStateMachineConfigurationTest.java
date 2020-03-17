package com.demo.trafficlight.configuration;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.state.TrafficLightState;
import com.demo.trafficlight.repository.TransitionIntervalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;

import java.util.Collection;
import java.util.EnumSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
class TrafficLightStateMachineConfigurationTest {

    @Autowired
    private StateMachine<TrafficLightState, TrafficLightEvent> stateMachine;

    @Autowired
    private TransitionIntervalRepository transitionIntervalRepository;

    @Test
    void testTrafficLightInitialState() {
        assertEquals(TrafficLightState.GREEN, stateMachine.getInitialState().getId());
    }

    @Test
    void testTrafficLightStateTransitions() {
        assumeTrue(TrafficLightState.GREEN == stateMachine.getState().getId());
        stateMachine.sendEvent(TrafficLightEvent.CAUTION);
        assertEquals(TrafficLightState.AMBER, stateMachine.getState().getId());
        stateMachine.sendEvent(TrafficLightEvent.STOP);
        assertEquals(TrafficLightState.RED, stateMachine.getState().getId());
        stateMachine.sendEvent(TrafficLightEvent.GO);
        assertEquals(TrafficLightState.GREEN, stateMachine.getState().getId());
    }

    @Test
    void testStates() {
        Collection<State<TrafficLightState, TrafficLightEvent>> states = stateMachine.getStates();
        assertTrue(states.stream()
                .map(State::getId)
                .collect(Collectors.toSet())
                .containsAll(EnumSet.allOf(TrafficLightState.class)));
    }

}