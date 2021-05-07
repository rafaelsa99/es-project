package com.es.projectbackend.controller;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.es.projectbackend.service.LAMetroService;
import com.google.gson.Gson;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class VehiclesController {
	@Autowired
	private LAMetroService laMetroService;
	
	private static final Logger logger = LogManager.getLogger(VehiclesController.class);
	
	@GetMapping("/vehicles")
	public String getAllPlanes(){
		logger.info("Request for live vehicles information");
		String planes = new Gson().toJson(laMetroService.getAllVehicles());
		return planes;
	}	
}
