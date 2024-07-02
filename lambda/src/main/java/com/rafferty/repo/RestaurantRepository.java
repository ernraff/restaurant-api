package com.rafferty.repo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.rafferty.model.Restaurant;


import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestaurantRepository {
//    private final AmazonDynamoDB client;
    private final DynamoDB dynamoDB;
//    private final Table table;
    private final String indexName = "cuisineIndex";
    private final DynamoDBMapper mapper;

    @Inject
    public RestaurantRepository(AmazonDynamoDB client) {
//        this.client = client;
        dynamoDB = new DynamoDB(client);
//        table = dynamoDB.getTable("restaurant-data");
        mapper = new DynamoDBMapper(client);
    }

    //query dynamodb using desired cuisine.  return up to specified number of results
    public List<Restaurant> getRestaurantsByCuisine (String cuisine, Integer maxNum) throws Exception{
        Restaurant restaurant = new Restaurant();
        restaurant.setCuisine(cuisine);

        // Create the query expression for the GSI
        DynamoDBQueryExpression<Restaurant> queryExpression = new DynamoDBQueryExpression<Restaurant>()
                .withHashKeyValues(restaurant)
                .withIndexName(indexName)
                .withConsistentRead(false)
                .withLimit(maxNum);

        return mapper.query(Restaurant.class, queryExpression);
    }

    //query dynamodb using restaurant id
    public List<Restaurant> getRestaurantById(String id) {
        Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
        map.put(":v1", new AttributeValue().withS("restaurant"));
        map.put(":v2", new AttributeValue().withS(id));

        DynamoDBQueryExpression<Restaurant> queryExpression = new DynamoDBQueryExpression<Restaurant>()
                .withKeyConditionExpression("business = :v1 and id = :v2")
                .withExpressionAttributeValues(map);

        return mapper.query(Restaurant.class, queryExpression);

    }


}
