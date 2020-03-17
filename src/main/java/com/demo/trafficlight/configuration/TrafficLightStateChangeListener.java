package com.demo.trafficlight.configuration;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.state.TrafficLightState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

@Slf4j
public class TrafficLightStateChangeListener implements StateMachineListener<TrafficLightState, TrafficLightEvent> {
    @Override
    public void stateChanged(State<TrafficLightState, TrafficLightEvent> fromState, State<TrafficLightState, TrafficLightEvent> toState) {
        if (fromState != null && toState != null) {
            log.info("State changed from {} to {}", fromState.getId().name(), toState.getId().name());
        }
    }

    @Override
    public void stateEntered(State<TrafficLightState, TrafficLightEvent> state) {

    }

    @Override
    public void stateExited(State<TrafficLightState, TrafficLightEvent> state) {

    }

    @Override
    public void eventNotAccepted(Message<TrafficLightEvent> message) {

    }

    @Override
    public void transition(Transition<TrafficLightState, TrafficLightEvent> transition) {

    }

    @Override
    public void transitionStarted(Transition<TrafficLightState, TrafficLightEvent> transition) {

    }

    @Override
    public void transitionEnded(Transition<TrafficLightState, TrafficLightEvent> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<TrafficLightState, TrafficLightEvent> stateMachine) {

    }

    @Override
    public void stateMachineStopped(StateMachine<TrafficLightState, TrafficLightEvent> stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine<TrafficLightState, TrafficLightEvent> stateMachine, Exception e) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<TrafficLightState, TrafficLightEvent> stateContext) {

    }
}
