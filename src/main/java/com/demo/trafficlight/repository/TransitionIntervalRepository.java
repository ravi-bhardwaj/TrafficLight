package com.demo.trafficlight.repository;

import com.demo.trafficlight.domain.event.TrafficLightEvent;
import com.demo.trafficlight.domain.transition.TransitionInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
@Transactional
public interface TransitionIntervalRepository extends JpaRepository<TransitionInterval, Long> {

    Optional<TransitionInterval> getTransitionIntervalByTimeOfDayAndDayOfWeekAndTrafficLightEvent(int timeOfDay, DayOfWeek dayOfWeek, TrafficLightEvent trafficLightEvent);

}
