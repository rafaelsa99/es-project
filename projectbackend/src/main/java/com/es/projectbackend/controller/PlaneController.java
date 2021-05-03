package com.es.projectbackend.controller;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.es.projectbackend.service.OpenSkyService;
import com.google.gson.Gson;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class PlaneController {
	@Autowired
	private OpenSkyService openSkyService;
	
	private static final Logger logger = LogManager.getLogger(PlaneController.class);
	
	@GetMapping("/planes")
	public String getAllPlanes(){
		logger.info("Request for live plane information");
		String planes = new Gson().toJson(openSkyService.getAllPlanes());
		return planes;
	}	
	
	@GetMapping("/history")
	public String getHistory(){
		logger.info("Request for altitude history");
		String history = new Gson().toJson(openSkyService.getHistory());
		return history;
	}	
}
