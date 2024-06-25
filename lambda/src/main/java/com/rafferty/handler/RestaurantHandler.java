package com.rafferty.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafferty.model.ApiGatewayResponse;
import com.rafferty.model.Restaurant;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RestaurantHandler implements RequestHandler<Object, ApiGatewayResponse>{
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    private final DynamoDB dynamoDB = new DynamoDB(client);
    private final Table table = dynamoDB.getTable("restaurant-data");
    private final String cuisineIndexName = "cuisineIndex";
    private final Index cuisineIndex = table.getIndex(cuisineIndexName);
    private final DynamoDBMapper mapper = new DynamoDBMapper(client);
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    List<Restaurant> getRestaurantsByCuisine (String cuisine, Integer maxNum) {
        Restaurant restaurant = new Restaurant();
        restaurant.setCuisine(cuisine);

        // Create the query expression for the GSI
        DynamoDBQueryExpression<Restaurant> queryExpression = new DynamoDBQueryExpression<Restaurant>()
                .withHashKeyValues(restaurant)
                .withIndexName(cuisineIndexName)
                .withConsistentRead(false)
                .withLimit(maxNum);

        try {
//            return mapper.queryPage(Restaurant.class, queryExpression);
            List<Restaurant> queryResult = mapper.query(Restaurant.class, queryExpression);
            return queryResult;
        } catch (Exception e) {
            System.out.println("DB Scanning Failed. " + e.getMessage());
            throw e;
        }
    }

    //query dynamodb using restaurant id
    List<Restaurant> getRestaurantById(String id) {
        Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
        map.put(":v1", new AttributeValue().withS("restaurant"));
        map.put(":v2", new AttributeValue().withS(id));

        DynamoDBQueryExpression<Restaurant> queryExpression = new DynamoDBQueryExpression<Restaurant>()
                .withKeyConditionExpression("business = :v1 and id = :v2")
                .withExpressionAttributeValues(map);

        List<Restaurant> restaurants = mapper.query(Restaurant.class, queryExpression);

        return restaurants;
    }
    @Override
    public  ApiGatewayResponse handleRequest(Object input, Context context) {
        LambdaLogger logger = context.getLogger();
        String responseBody = null;

        try {
            Map<String, Object> inputMap = (Map<String, Object>) input;

            // Check if the input contains query string parameters
            if (inputMap.containsKey("queryStringParameters")) {
                logger.log("Input map contains queryStringParameters key.");
                Map<String, String> queryStringParameters = (Map<String, String>) inputMap.get("queryStringParameters");
                String cuisine = queryStringParameters.get("cuisine");
                String id = queryStringParameters.get("id");

                if (cuisine != null) {
                    // Log the cuisine parameter
                    logger.log("Cuisine: " + cuisine);

                    // Call getRestaurant method
                    List<Restaurant> cuisineResult = getRestaurantsByCuisine(cuisine, 5); // Adjust the lastEvaluatedKey and maxNum as needed
                    responseBody = objectMapper.writeValueAsString(cuisineResult);
                } else if (id != null){
                    logger.log("Id = " + id);


                    //call get Restaurant by id
                    List<Restaurant> idResult = getRestaurantById(id);
                    responseBody = objectMapper.writeValueAsString(idResult);
                }
            } else {
                logger.log("No query string parameters found");
                responseBody = "No query string parameters found";
            }
        } catch (Exception e) {
            logger.log("Error processing request: " + e.getMessage());
            responseBody = "Error processing request: " + e.getMessage();
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("X-amazon-author", "Erin");
        headers.put("X-amazon-apiVersion", "v1");

        ApiGatewayResponse response = new ApiGatewayResponse(responseBody, 200, headers, false);

        logger.log("Status code: " + response.getStatusCode() + ", " + "Body: " + response.getBody());

        return response;
    }

}

