package org.marta.dao.repository;

import java.util.List;

import org.marta.dao.DynamoDAO;
import org.marta.model.Route;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class RouteRepository extends DynamoDAO implements Repository<Route> {

	public RouteRepository() {
		super();
	}

	@Override
	public Class<Route> getClassType() {
		return Route.class;
	}

	@Override
	public DynamoDBMapper getDynamoDBMapper() {
		return mapper;
	}
	
	public List<Route> findAllRoutes() {
		 return mapper.scan(Route.class, new DynamoDBScanExpression());
	}
	
	public Route getByRouteId(String routeId) {
		Route route = new Route();
		route.setRouteId(routeId);
		
		DynamoDBQueryExpression<Route> routeQuery = new DynamoDBQueryExpression<Route>();
		routeQuery.setHashKeyValues(route);
		
		List<Route> routes = mapper.query(Route.class, routeQuery);
		return routes.get(0);
	}
	
    public void saveOrUpdateRoute(Route route) {
        mapper.save(route);
    }
    
    public void saveOrUpdateRoutes(List<Route> routes) {
        mapper.batchSave(routes);
    }
}