package org.marta.dao.repository;

import java.util.List;

import org.marta.dao.DynamoDAO;
import org.marta.model.Stop;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class StopRepository extends DynamoDAO implements Repository<Stop> {

	public StopRepository() {
		super();
	}

	@Override
	public Class<Stop> getClassType() {
		return Stop.class;
	}

	@Override
	public DynamoDBMapper getDynamoDBMapper() {
		return mapper;
	}
	
    public List<Stop> findAllStops() {
        return mapper.scan(Stop.class, new DynamoDBScanExpression());
    }
	
	public Stop getByStopId(String stopId) {
		Stop stop = new Stop();
		stop.setStopId(stopId);
		
		DynamoDBQueryExpression<Stop> stopIdQuery = new DynamoDBQueryExpression<Stop>();
		stopIdQuery.setHashKeyValues(stop);
		
		List<Stop> stops = mapper.query(Stop.class, stopIdQuery);
		return stops.get(0);
	}
	
    public void saveOrUpdateStop(Stop stop) {
        mapper.save(stop);
    }
    
    public void saveOrUpdateStops(List<Stop> stops) {
        mapper.batchSave(stops);
    }
}