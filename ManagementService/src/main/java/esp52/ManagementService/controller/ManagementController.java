package esp52.ManagementService.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import esp52.ManagementService.service.ManagementService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class ManagementController {

	private ManagementService managementService = new ManagementService();
	private static final Logger logger = LogManager.getLogger(ManagementController.class);
	
	@GetMapping("/parkinglots")
	public String getAllParks(){
		logger.info("Request for Parking Lots Information");
		return managementService.getParkingInfo();
	}
	
	@GetMapping("/agencies")
	public String getAllAgencies(){
		logger.info("Request for informations about all agencies");
		return managementService.getAllAgenciesInfo();
	}
	
	@GetMapping("/events")
	public String getAllEvents(){
		logger.info("Request for all parking events");
		return managementService.getEvents(false);
	}
	
	@GetMapping("/events/last")
	public String getLastEvents(){
		logger.info("Request for the last parking event");
		return managementService.getEvents(true);
	}
	
	@GetMapping("/history")
	public String getHistory(){
		logger.info("Request for history information");
		return managementService.getHistory();
	}
	
	@GetMapping("/agencies/{agency}")
	public String getAgency(@PathVariable("agency") String agency){
		logger.info("Request for informations about the agency " + agency);
		return managementService.getAgencyInfo(agency);
	}
}
