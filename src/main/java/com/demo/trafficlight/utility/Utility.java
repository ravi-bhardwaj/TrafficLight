package com.demo.trafficlight.utility;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.transition.TransitionInterval;
import com.demo.trafficlight.repository.TransitionIntervalRepository;

import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.Random;
import java.util.stream.IntStream;

public class Utility {

    /**
     * Utility method to populate Transition_Interval table with sample data
     */
    public static void generateSampleData(TransitionIntervalRepository repository) {
        EnumSet.allOf(DayOfWeek.class)
                .forEach(dayOfWeek -> {
                    IntStream.rangeClosed(0, 23)
                            .forEach(hour -> {
                                EnumSet.allOf(TrafficLightEvent.class).forEach(trafficLightEvent -> {
                                    TransitionInterval transitionInterval = TransitionInterval
                                            .builder()
                                            .timeOfDay(hour)
                                            .dayOfWeek(dayOfWeek)
                                            .trafficLightEvent(trafficLightEvent)
                                            .transitionTime(new Random()
                                                    .longs(1, 5)
                                                    .limit(1)
                                                    .findFirst()
                                                    .getAsLong()).build();
                                    repository.save(transitionInterval);
                                });
                            });
                });
    }
}
