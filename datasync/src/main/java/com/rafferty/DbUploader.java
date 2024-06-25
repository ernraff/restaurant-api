package com.rafferty;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.rafferty.model.Restaurant;

public class DbUploader {
    //build the dynamodb client
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    private final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);

    public void writeToDb(Restaurant restaurant) {
        dynamoDBMapper.save(restaurant);
    }
}
