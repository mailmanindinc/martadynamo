package org.marta.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.univocity.parsers.annotations.Parsed;

@DynamoDBTable(tableName = "STOP")
public class Stop implements Serializable {

	private static final long serialVersionUID = 3127359097645144549L;
	
	@Parsed(field = "stop_id")
	@DynamoDBHashKey
	private String stopId;
	
	@Parsed(field = "stop_code")
	@DynamoDBAttribute
	private String stopCode;
	
	@Parsed(field = "stop_name")
	@DynamoDBAttribute
	private String stopName;
	
	@Parsed(field = "stop_lat")
	@DynamoDBAttribute
	private Double stopLat;
	
	@Parsed(field = "stop_lon")
	@DynamoDBAttribute
	private Double stopLon;

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public String getStopCode() {
		return stopCode;
	}

	public void setStopCode(String stopCode) {
		this.stopCode = stopCode;
	}

	public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	public Double getStopLat() {
		return stopLat;
	}

	public void setStopLat(Double stopLat) {
		this.stopLat = stopLat;
	}

	public Double getStopLon() {
		return stopLon;
	}

	public void setStopLon(Double stopLon) {
		this.stopLon = stopLon;
	}
}
