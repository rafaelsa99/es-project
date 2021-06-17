package esp52.VehiclesService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import esp52.VehiclesService.service.VehiclesService;

@RestController
@RequestMapping("/")
public class VehiclesController {

	private static final Logger logger = LoggerFactory.getLogger(VehiclesController.class);
	@Autowired VehiclesService vehiclesService;
	
	@GetMapping("{agency}/vehicles/")
	public String getVehicles(@PathVariable("agency") String agency) {
		logger.info("Request for vehicles from agency " + agency);
		return vehiclesService.getVehicles(agency);
	}
	
	@GetMapping("{agency}/predictions/")
	public String getPredictions(@PathVariable("agency") String agency) {
		logger.info("Request for predictions from agency " + agency);
		return vehiclesService.getPredictions(agency);
	}
	
	@GetMapping("history/")
	public String getHistory() {
		logger.info("Request for history information");
		return vehiclesService.getHistory();
	}
}
