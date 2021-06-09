package esp52.ParkingLotsService.service;

import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import esp52.ParkingLotsService.kafka.KafkaProducer;
import esp52.ParkingLotsService.models.ParkingLotation;

@Component
public class ParksService {

	private static final String TOPIC_EVENTS = "events";
	private static final Logger logger = LogManager.getLogger(ParksService.class);
	private HashMap<String, ParkingLotation> lotations = new HashMap<>();
	private ParkingLotation parklots;
	@Autowired KafkaProducer kafkaProducer;
	
	@KafkaListener(topics = "parking", groupId = "lametro")
	public void listenEvents(String message) {
		ParkingLotation pl = new Gson().fromJson(message, ParkingLotation.class);
		parklots = pl;
		pl.setUpdated((int)getUnixTime() - pl.getUpdated());
		if(lotations.containsKey(pl.getName())) {
			ParkingLotation old = lotations.replace(pl.getName(), pl);
			checkParkingEvents(pl, old);
		} else {
			lotations.put(pl.getName(), pl);
			checkParkingEvents(pl);
		}
		logger.info("Updating Parking Lotations from Parking Lot " + pl.getName());
	}

	public ParkingLotation getTestPark(int total, int free, int totald, int dfree) {
		ParkingLotation p1 = new ParkingLotation();
		p1.setUpdated(0);
		p1.setName("testPark1");
		p1.setTotal(total);
                p1.setFree(free);
		p1.setDisabledtotal(totald);
		p1.setDisabledfree(dfree);
		
		return p1;
	}
	
	public long getUnixTime() {
		long unixTime = System.currentTimeMillis() / 1000L;
		return unixTime;
	}
	
	public String getLotations() {
		String lotationsJson =  new Gson().toJson(lotations.values());
		return lotationsJson;
	}
	
	public int getFree() {
		return parklots.getFree();
	}
	
	private void checkParkingEvents(ParkingLotation pl) {
		if(pl.getTotal() == pl.getFree())
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " is totally free!");
		if(pl.getDisabledtotal() == pl.getDisabledfree())
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " for disabled is totally free!");
		if(pl.getFree() > 0 && pl.getFree() <= 50)
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " has a reduced number of free parking spaces!");
		if(pl.getDisabledfree() > 0 && pl.getDisabledfree() <= 3)
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " has a reduced number of free parking spaces for disabled!");
		if(pl.getFree() == 0)
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " has the parking spaces full!");
		if(pl.getDisabledfree() == 0)
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " has the disabled parking spaces full!");
	}
	
	private void checkParkingEvents(ParkingLotation pl, ParkingLotation oldLotation) {
		if(pl.getTotal() == pl.getFree() && oldLotation.getTotal() != oldLotation.getFree())
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " is totally free!");
		if(pl.getDisabledtotal() == pl.getDisabledfree() && oldLotation.getDisabledtotal() != oldLotation.getDisabledfree())
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " for disabled is totally free!");
		if((pl.getFree() > 0 && pl.getFree() <= 50) && (oldLotation.getFree() == 0 || oldLotation.getFree() > 50))
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " has a reduced number of free parking spaces!");
		if((pl.getDisabledfree() > 0 && pl.getDisabledfree() <= 3) && (oldLotation.getDisabledfree() == 0 || oldLotation.getDisabledfree() > 3))
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " has a reduced number of free parking spaces for disabled!");
		if(pl.getFree() == 0 && oldLotation.getFree() != 0)
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " has the parking spaces full!");
		if(pl.getDisabledfree() == 0 && oldLotation.getDisabledfree() != 0)
			kafkaProducer.sendMessage(TOPIC_EVENTS, "Park " + pl.getName() + " has the disabled parking spaces full!");
	}
}
