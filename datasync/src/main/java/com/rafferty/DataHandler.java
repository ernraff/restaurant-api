package com.rafferty;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.rafferty.model.ApiGatewayResponse;
import com.rafferty.model.Restaurant;
import com.rafferty.model.YelpResponse;


//1. invoke the yelp API to obtain data from NYC restaurants for cuisines from a given list
//
//2. parse response and write restaurant data to DB
//
public class DataHandler implements RequestHandler<Object, ApiGatewayResponse> {

    //build the dynamodb client
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    private final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);
    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public ApiGatewayResponse handleRequest(Object input, Context context) {

        SecretRetriever secretRetriever = new SecretRetriever();
        //yelp fusion api url for restaurants in NYC
        String url = "https://api.yelp.com/v3/businesses/search?location=NYC&businesses=restaurant";
        String apiKey = secretRetriever.requestSecret("YelpAuth");

        List<String> cuisines = Arrays.asList("thai", "chinese", "italian", "korean", "american", "japanese");

        //create http client
        var client = HttpClient.newHttpClient();

        DbUploader dbUploader = new DbUploader();

        for(String cuisine: cuisines) {
            //build request URL
            StringBuilder sb = new StringBuilder();
            sb.append(url).append("&categories=").append(cuisine);

            //create http request
            try {
                var request = HttpRequest.newBuilder()
                        .header("Authorization", apiKey)
                        .uri(new URI(sb.toString())).build();

                //use client to send the request
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                YelpResponse yelpResponse = mapper.readValue(response.body(), YelpResponse.class);
                List<Restaurant> restaurants = yelpResponse.getBusinesses();

                //write data to dynamoDB
                LambdaLogger logger = context.getLogger();
                for(Restaurant restaurant : restaurants) {
                    //set cuisine attribute before writing to db
                    restaurant.setCuisine(cuisine);
                    dbUploader.writeToDb(restaurant);
                }

            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String body = "I called the yelp API!";

        HashMap<String, String> headers = new HashMap<>();
        headers.put("X-amazon-author", "Erin");
        headers.put("X-amazon-apiVersion", "v1");

        return new ApiGatewayResponse(body, 200, headers, false);

    }
}
