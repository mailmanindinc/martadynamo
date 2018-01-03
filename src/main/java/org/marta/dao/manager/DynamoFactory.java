package org.marta.dao.manager;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoFactory {

	//private static final String ACCESS_KEY = System.getenv("DYNAMO_ACCESS_KEY");
	//private static final String SECRET_KEY = System.getenv("DYNAMO_SECRET_KEY");

	private static AmazonDynamoDB DYNAMO_INSTANCE = null;
	private static DynamoDBMapper DYNAMO_MAPPER = null;

	private DynamoFactory() {
	}

	public static AmazonDynamoDB getDynamoInstance() {
		if (DYNAMO_INSTANCE == null) {
			AmazonDynamoDBClientBuilder amazonDynamoDBClientBuilder = AmazonDynamoDBClientBuilder.standard()
					.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://dynamodb.us-east-2.amazonaws.com", "us-east-2"));
			synchronized (DynamoFactory.class) {
				if (DYNAMO_INSTANCE == null) {
					DYNAMO_INSTANCE = amazonDynamoDBClientBuilder.build();
					DYNAMO_MAPPER = new DynamoDBMapper(DYNAMO_INSTANCE);
				}
			}
		}
		return DYNAMO_INSTANCE;
	}

/*	private static AWSCredentialsProvider getAwsCredentials() {
		return new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
	}*/

	public static DynamoDBMapper mapper() {
		getDynamoInstance();
		return DYNAMO_MAPPER;
	}
}