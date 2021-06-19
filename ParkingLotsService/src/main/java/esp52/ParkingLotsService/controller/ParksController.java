package esp52.ParkingLotsService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import esp52.ParkingLotsService.service.ParksService;

@RestController
@RequestMapping("/")
public class ParksController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //private static final Logger logger = LogManager.getLogger(ParksController.class);
    @Autowired
    ParksService parksService;

    @GetMapping("lotations/")
    public String getLotations() {
        this.logger.info("Request for parking lotations");
        return parksService.getLotations();
    }

    @GetMapping("history/")
    public String getHistory() {
        this.logger.info("Request for history information");
        return parksService.getHistory();
    }

}
