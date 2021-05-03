package com.es.projectbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "planes")
@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public class Plane {
	
	@Id
	private String icao24; // Unique ICAO 24-bit address of the transponder in hex string representation.
	@Column(name = "callsign")
	private String callsign; // Callsign of the vehicle (8 chars). Can be null if no callsign has been received.
	@Column(name = "origin_country")
	private String origin_country; // Country name inferred from the ICAO 24-bit address.
	@Transient
	private Double time_position; // Unix timestamp (seconds) for the last position update. Can be null if no position report was received by OpenSky within the past 15s.
	@Transient
	private Double last_contact; // Unix timestamp (seconds) for the last update in general. This field is updated for any new, valid message received from the transponder.
	@Transient
	private Double longitude; // WGS-84 longitude in decimal degrees. Can be null.
	@Transient
	private Double latitude; // WGS-84 latitude in decimal degrees. Can be null.
	@Transient
	private Double baro_altitude; // Barometric altitude in meters. Can be null.
	@Transient
	private boolean on_ground; // Boolean value which indicates if the position was retrieved from a surface position report.
	@Transient
	private Double velocity; // Velocity over ground in m/s. Can be null.
	@Transient
	private Double true_track; // True track in decimal degrees clockwise from north (north=0°). Can be null.
	@Transient
	private Double vertical_rate; // Vertical rate in m/s. A positive value indicates that the airplane is climbing, a negative value indicates that it descends. Can be null.
	@Transient
	private int[] sensors; // IDs of the receivers which contributed to this state vector. Is null if no filtering for sensor was used in the request.
	@Column(name = "geo_altitude")
	private Double geo_altitude; // Geometric altitude in meters. Can be null.
	@Transient
	private String squawk; // The transponder code aka Squawk. Can be null.
	@Transient
	private boolean spi; // Whether flight status indicates special purpose indicator.
	@Transient
	private int position_source; // Origin of this state’s position: 0 = ADS-B, 1 = ASTERIX, 2 = MLAT

	public String getIcao24() {
		return icao24;
	}

	public void setIcao24(String icao24) {
		this.icao24 = icao24;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getOrigin_country() {
		return origin_country;
	}

	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}

	public Double getTime_position() {
		return time_position;
	}

	public void setTime_position(Double time_position) {
		this.time_position = time_position;
	}

	public Double getLast_contact() {
		return last_contact;
	}

	public void setLast_contact(Double last_contact) {
		this.last_contact = last_contact;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getBaro_altitude() {
		return baro_altitude;
	}

	public void setBaro_altitude(Double baro_altitude) {
		this.baro_altitude = baro_altitude;
	}

	public boolean isOn_ground() {
		return on_ground;
	}

	public void setOn_ground(boolean on_ground) {
		this.on_ground = on_ground;
	}

	public Double getVelocity() {
		return velocity;
	}

	public void setVelocity(Double velocity) {
		this.velocity = velocity;
	}

	public Double getTrue_track() {
		return true_track;
	}

	public void setTrue_track(Double true_track) {
		this.true_track = true_track;
	}

	public Double getVertical_rate() {
		return vertical_rate;
	}

	public void setVertical_rate(Double vertical_rate) {
		this.vertical_rate = vertical_rate;
	}

	public int[] getSensors() {
		return sensors;
	}

	public void setSensors(int[] sensors) {
		this.sensors = sensors;
	}

	public Double getGeo_altitude() {
		return geo_altitude;
	}

	public void setGeo_altitude(Double geo_altitude) {
		this.geo_altitude = geo_altitude;
	}

	public String getSquawk() {
		return squawk;
	}

	public void setSquawk(String squawk) {
		this.squawk = squawk;
	}

	public boolean isSpi() {
		return spi;
	}

	public void setSpi(boolean spi) {
		this.spi = spi;
	}

	public int getPosition_source() {
		return position_source;
	}

	public void setPosition_source(int position_source) {
		this.position_source = position_source;
	}


}
