package esp52.StaticInfo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esp52.StaticInfo.data.StaticInfoData;

@RestController
@RequestMapping("/")
public class StaticInfoController {

	private static final Logger logger = LogManager.getLogger(StaticInfoController.class);
	private StaticInfoData staticInfoData = new StaticInfoData();
	
	@GetMapping("agencies/")
	public String getAgencies(){
		logger.info("Request for agencies");
		return staticInfoData.getAgencies();
	}
	
	@GetMapping("parkinglots/")
	public String getParkingLots(){
		logger.info("Request for parking lots");
		return staticInfoData.getParkingLots();
	}
	
	@GetMapping("{agency}/routes/")
	public String getRoutes(@PathVariable("agency") String agency){
		logger.info("Request for routes from agency " + agency);
		return staticInfoData.getRoutes(agency);
	}
	
	@GetMapping("{agency}/routes/{route_id}/stops/")
	public String getRouteStops(@PathVariable("agency") String agency, @PathVariable("route_id") String route_id){
		logger.info("Request for stops from route with id " + route_id +" from agency " + agency);
		return staticInfoData.getRouteStops(agency, route_id);
	}
}
