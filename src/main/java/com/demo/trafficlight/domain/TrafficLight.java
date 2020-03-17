package com.demo.trafficlight.domain;

import com.demo.trafficlight.domain.state.TrafficLightState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Slf4j
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TrafficLight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TrafficLightState trafficLightState;

    public boolean isRed() {
        return this.trafficLightState == TrafficLightState.RED;
    }

    public boolean isAmber() {
        return this.trafficLightState == TrafficLightState.AMBER;
    }

    public boolean isGreen() {
        return this.trafficLightState == TrafficLightState.GREEN;
    }
}
