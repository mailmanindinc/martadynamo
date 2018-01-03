package org.marta.dao.repository;

import java.util.List;

import org.marta.dao.DynamoDAO;
import org.marta.model.Trip;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class TripRepository extends DynamoDAO implements Repository<Trip> {

	public TripRepository() {
		super();
	}

	@Override
	public Class<Trip> getClassType() {
		return Trip.class;
	}

	@Override
	public DynamoDBMapper getDynamoDBMapper() {
		return mapper;
	}
	
	public List<Trip> findAllTrips() {
		return mapper.scan(Trip.class, new DynamoDBScanExpression());
	}
	
	public Trip getByTripId(String tripId) {
		Trip trip = new Trip();
		trip.setTripId(tripId);
		
		DynamoDBQueryExpression<Trip> tripQuery = new DynamoDBQueryExpression<Trip>();
		tripQuery.setHashKeyValues(trip);
		
		List<Trip> trips = mapper.query(Trip.class, tripQuery);
		return trips.get(0);
	}
	
	public Trip getByTripRouteId(String tripId, String routeId) {
		Trip trip = new Trip();
		trip.setTripId(tripId);
		trip.setRouteId(routeId);
		
		DynamoDBQueryExpression<Trip> tripQuery = new DynamoDBQueryExpression<Trip>();
		tripQuery.setHashKeyValues(trip);
		
		List<Trip> trips = mapper.query(Trip.class, tripQuery);
		return trips.get(0);
	}
	
    public void saveOrUpdateTrip(Trip trip) {
        mapper.save(trip);
    }
    
    public void saveOrUpdateTrips(List<Trip> trips) {
        mapper.batchSave(trips);
    }

}