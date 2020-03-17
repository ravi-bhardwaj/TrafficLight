package com.demo.trafficlight.service;

import com.demo.trafficlight.domain.TrafficLight;
import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.state.TrafficLightState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TrafficLightServiceImpl implements TrafficLightService {

    private StateMachine<TrafficLightState, TrafficLightEvent> stateMachine;

    /**
     * This method is used to transition the state oa traffic light
     **/
    @Override
    public void changeState(TrafficLight trafficLight) {
        //Get the Current State from State Machine
        TrafficLightState trafficLightState = stateMachine.getState().getId();
        log.info("Current state : {} ", trafficLightState);
        //Send the Transition Event
        stateMachine.sendEvent(getTrafficLightEvent(trafficLightState));
        //Set the New State of the Traffic Light
        trafficLight.setTrafficLightState(stateMachine.getState().getId());
    }

    private TrafficLightEvent getTrafficLightEvent(TrafficLightState currentState) {
        if (currentState == TrafficLightState.GREEN) {
            return TrafficLightEvent.CAUTION;
        } else if (currentState == TrafficLightState.AMBER) {
            return TrafficLightEvent.STOP;
        } else if (currentState == TrafficLightState.RED) {
            return TrafficLightEvent.GO;
        } else {
            throw new RuntimeException("Invalid State. Can not determine Transition Event.");
        }
    }
}
