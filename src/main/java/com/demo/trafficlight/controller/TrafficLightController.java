package com.demo.trafficlight.controller;

import com.demo.trafficlight.utility.Utility;
import com.demo.trafficlight.domain.TrafficLight;
import com.demo.trafficlight.repository.TransitionIntervalRepository;
import com.demo.trafficlight.service.TrafficLightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class TrafficLightController {

    private TrafficLightService trafficLightService;
    private TransitionIntervalRepository repository;


    /**
     * This is the endpoint to show the landing page.
     **/
    @GetMapping("/trafficLight")
    public String trafficLight(Model model) {
        model.addAttribute("trafficLight", new TrafficLight());
        return "TrafficLight";
    }

    /**
     * This is the endpoint invoked when user click on the change state button.
     **/
    @GetMapping("/trafficLight/changeState")
    public String changeState(Model model) {
        TrafficLight trafficLight = new TrafficLight();
        model.addAttribute("trafficLight", trafficLight);
        trafficLightService.changeState(trafficLight);
        return "TrafficLight";
    }

    @GetMapping("/trafficLight/sampleData")
    public ResponseEntity<String> setupSampleData() {
        Utility.generateSampleData(repository);
        return ResponseEntity.ok("Sample Data for Transition Intervals successfully created.");
    }

}
