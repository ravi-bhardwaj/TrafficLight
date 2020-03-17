package com.demo.trafficlight.domain.transition;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransitionInterval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TrafficLightEvent trafficLightEvent;

    private Long transitionTime;

    private int timeOfDay;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

}
