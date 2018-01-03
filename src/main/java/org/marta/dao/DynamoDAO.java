package org.marta.dao;

import org.marta.dao.manager.DynamoFactory;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDAO {
	
	protected final DynamoDBMapper mapper;

	protected DynamoDAO() {
		this.mapper =  DynamoFactory.mapper();
	}
}