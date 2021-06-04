package esp52.externaAPI.controller;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import esp52.externaAPI.service.LiveInfoService;

@RestController
@RequestMapping("/")
public class StaticInfoController {
	
	//private static final Logger logger = LogManager.getLogger(StaticInfoController.class);
	@Autowired private LiveInfoService liveInfoService;
	
	@GetMapping("agencies/")
	public String getAgencies(){
		//logger.info("Request for agencies");
		return liveInfoService.getAgencies();
	}
	
	@GetMapping("{agency}/parkinglots/")
	public String getParkingLots(@PathVariable("agency") String agency){
		//logger.info("Request for parking lots from agency " + agency);
		return liveInfoService.getParkingLots(agency);
	}
	
	@GetMapping("{agency}/routes/")
	public String getRoutes(@PathVariable("agency") String agency){
		//logger.info("Request for routes from agency " + agency);
		return liveInfoService.getRoutes(agency);
	}
	
	@GetMapping("{agency}/routes/{route_id}/stops/")
	public String getRouteStops(@PathVariable("agency") String agency, @PathVariable("route_id") String route_id){
		//logger.info("Request for stops from route with id " + route_id +" from agency " + agency);
		return liveInfoService.getRouteStops(agency, route_id);
	}
}
