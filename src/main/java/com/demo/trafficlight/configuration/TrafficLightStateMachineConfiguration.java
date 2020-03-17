package com.demo.trafficlight.configuration;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.state.TrafficLightState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/*
 * This class configures the Different States and Transitions for a Traffic Light using Spring State Machine Libraries.
 **/
@Slf4j
@Configuration
@EnableStateMachine
@Scope(scopeName = "prototype")
public class TrafficLightStateMachineConfiguration extends EnumStateMachineConfigurerAdapter<TrafficLightState, TrafficLightEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<TrafficLightState, TrafficLightEvent> states) throws Exception {
        //Configure the Initial State and set all the possible States.
        states.withStates()
                .initial(TrafficLightState.GREEN)
                .states(EnumSet.allOf(TrafficLightState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TrafficLightState, TrafficLightEvent> transitions) throws Exception {
        //Define the Transitions.
        transitions
                .withExternal()
                .source(TrafficLightState.RED)
                .target(TrafficLightState.GREEN)
                .event(TrafficLightEvent.GO)
                .and()
                .withExternal()
                .source(TrafficLightState.GREEN)
                .target(TrafficLightState.AMBER)
                .event(TrafficLightEvent.CAUTION)
                .and()
                .withExternal()
                .source(TrafficLightState.AMBER)
                .target(TrafficLightState.RED)
                .event(TrafficLightEvent.STOP);
    }

    /*
     * Auto start the State Machine and register a State Change Listener.
     **/
    @Override
    public void configure(StateMachineConfigurationConfigurer<TrafficLightState, TrafficLightEvent> config) throws Exception {
        config.withConfiguration()
                .autoStartup(Boolean.TRUE)
                .listener(new TrafficLightStateChangeListener());
    }
}
