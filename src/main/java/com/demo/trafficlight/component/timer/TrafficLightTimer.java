package com.demo.trafficlight.component.timer;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.state.TrafficLightState;
import com.demo.trafficlight.domain.transition.TransitionInterval;
import com.demo.trafficlight.repository.TransitionIntervalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Component
public class TrafficLightTimer {

    private static final Long DEFAULT_TRANSITION_INTERVAL = 2L;
    private StateMachine<TrafficLightState, TrafficLightEvent> stateMachine;
    private TransitionIntervalRepository transitionIntervalRepository;

    /**
     * This method starts he Traffic Light State Transition.
     * The Default Transition time is 2 seconds.
     * If a Transition time is found in DB for the Time of Day (Hour) and the Day of Week, that value is used for Transitions.
     **/
    public void start() {
        //Get the even to send
        TrafficLightEvent trafficLightEvent = getTrafficLightEvent();
        //Set the event on the Traffic Light
        Long transitionInterval = getTransitionInterval(trafficLightEvent);
        //Send the transition event.
        changeState(transitionInterval, trafficLightEvent);
    }

    /**
     * Returns the event to send based on the state.
     **/
    private TrafficLightEvent getTrafficLightEvent() {
        TrafficLightState currentState = stateMachine.getState().getId();
        log.info("Current State : {}", currentState);
        TrafficLightEvent trafficLightEvent = null;
        if (currentState == TrafficLightState.GREEN) {
            trafficLightEvent = TrafficLightEvent.CAUTION;
        }
        if (currentState == TrafficLightState.AMBER) {
            trafficLightEvent = TrafficLightEvent.STOP;
        }
        if (currentState == TrafficLightState.RED) {
            trafficLightEvent = TrafficLightEvent.GO;
        }
        return trafficLightEvent;
    }

    /**
     * This method times sending of the events.
     **/
    private void changeState(Long transitionInterval, TrafficLightEvent trafficLightEvent) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        if (transitionInterval != null) {
            log.info("Waiting for {} seconds...", transitionInterval);
            executorService.schedule(() -> sendTrafficLightChangeEventAndStartTimer(trafficLightEvent), transitionInterval, TimeUnit.SECONDS);
        }
    }

    /**
     * This method gets finds the transition interval time from the DB based on the Time of the Day(Hour), Day of Week and the Event.
     **/
    private Long getTransitionInterval(TrafficLightEvent trafficLightEvent) {
        return transitionIntervalRepository
                .getTransitionIntervalByTimeOfDayAndDayOfWeekAndTrafficLightEvent(getTimeOfDay(), getDayOfWeek(), trafficLightEvent)
                .map(TransitionInterval::getTransitionTime)
                .orElse(DEFAULT_TRANSITION_INTERVAL);
    }

    /**
     * This method sends a event to the State Machine and call the timer again for the next event.
     **/
    private void sendTrafficLightChangeEventAndStartTimer(TrafficLightEvent trafficLightEvent) {
        stateMachine.sendEvent(trafficLightEvent);
        start();
    }

    private DayOfWeek getDayOfWeek() {
        return LocalDate.now().getDayOfWeek();
    }

    private int getTimeOfDay() {
        return LocalTime.now().getHour();
    }

}
