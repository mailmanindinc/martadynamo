package org.marta.dao.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

public interface Repository<T> {

	default T findOne(String id) {
		return getDynamoDBMapper().load(getClassType(), id);
	}

	default List<T> getObjects() {
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		PaginatedScanList<T> list = getDynamoDBMapper().scan(getClassType(), expression);
		return list.stream().collect(Collectors.toList());
	}

	default T save(T object) {
		getDynamoDBMapper().save(object);
		return object;
	}

	Class<T> getClassType();

	DynamoDBMapper getDynamoDBMapper();
}