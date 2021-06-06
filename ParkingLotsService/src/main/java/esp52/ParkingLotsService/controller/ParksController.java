package esp52.ParkingLotsService.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esp52.ParkingLotsService.service.ParksService;

@RestController
@RequestMapping("/")
public class ParksController {

    private static final Logger logger = LogManager.getLogger(ParksController.class);
    @Autowired
    ParksService parksService;

    @GetMapping("lotations/")
    public String getLotations() {
        logger.info("Request for parking lotations");
        return parksService.getLotations();
    }

}
