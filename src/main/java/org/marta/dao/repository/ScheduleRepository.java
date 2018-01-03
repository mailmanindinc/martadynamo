package org.marta.dao.repository;

import java.util.List;

import org.marta.dao.DynamoDAO;
import org.marta.model.Schedule;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class ScheduleRepository extends DynamoDAO implements Repository<Schedule> {

	public ScheduleRepository() {
		super();
	}

	@Override
	public Class<Schedule> getClassType() {
		return Schedule.class;
	}

	@Override
	public DynamoDBMapper getDynamoDBMapper() {
		return mapper;
	}

	public List<Schedule> findAllRoutes() {
		return mapper.scan(Schedule.class, new DynamoDBScanExpression());
	}

	public Schedule getByStopId(String stopId) {
		Schedule schedule = new Schedule();
		schedule.setStopId(stopId);

		DynamoDBQueryExpression<Schedule> scheduleQuery = new DynamoDBQueryExpression<Schedule>();
		scheduleQuery.setHashKeyValues(schedule);

		List<Schedule> schedules = mapper.query(Schedule.class, scheduleQuery);
		return schedules.get(0);
	}

	public Schedule getByTripId(String tripId) {
		Schedule schedule = new Schedule();
		schedule.setTripId(tripId);

		DynamoDBQueryExpression<Schedule> scheduleQuery = new DynamoDBQueryExpression<Schedule>();
		scheduleQuery.setHashKeyValues(schedule);

		List<Schedule> schedules = mapper.query(Schedule.class, scheduleQuery);
		return schedules.get(0);
	}

	public Schedule getByTripStopId(String tripId, String stopId) {
		Schedule schedule = new Schedule();
		schedule.setTripId(tripId);
		schedule.setStopId(stopId);

		DynamoDBQueryExpression<Schedule> scheduleQuery = new DynamoDBQueryExpression<Schedule>();
		scheduleQuery.setHashKeyValues(schedule);

		List<Schedule> schedules = mapper.query(Schedule.class, scheduleQuery);
		return schedules.get(0);
	}

	public void saveOrUpdateSchedule(Schedule schedule) {
		mapper.save(schedule);
	}

	public void saveOrUpdateSchedules(List<Schedule> schedules) {
		mapper.batchSave(schedules);
	}
}