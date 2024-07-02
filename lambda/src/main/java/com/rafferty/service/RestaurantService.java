package com.rafferty.service;

import com.rafferty.model.Restaurant;
import com.rafferty.repo.RestaurantRepository;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;

public class RestaurantService {
    private final ObjectMapper objectMapper;
    private final RestaurantRepository repository;
    private static Logger LOG = LoggerFactory.getLogger(RestaurantService.class);

    @Inject
    public RestaurantService(RestaurantRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }
    public String getRestaurants(Object input) {
        String responseBody = null;
        try {
            Map<String, Object> inputMap = (Map<String, Object>) input;

            // Check if the input contains query string parameters
            if (inputMap.containsKey("queryStringParameters")) {
                Map<String, String> queryStringParameters = (Map<String, String>) inputMap.get("queryStringParameters");
                String cuisine = queryStringParameters.get("cuisine");
                String id = queryStringParameters.get("id");

                if (cuisine != null) {
                    LOG.info("Cuisine: " + cuisine);
                    List<Restaurant> cuisineResult = repository.getRestaurantsByCuisine(cuisine, 5);
                    responseBody = objectMapper.writeValueAsString(cuisineResult);
                } else if (id != null){
                    LOG.info("ID: " + id);
                    List<Restaurant> idResult = repository.getRestaurantById(id);
                    responseBody = objectMapper.writeValueAsString(idResult);
                }
            } else {
                responseBody = "No query string parameters found";
                LOG.error(responseBody);
            }
        } catch (Exception e) {
            responseBody = "Error processing request: " + e.getMessage();
            LOG.error(responseBody);
        }

        return responseBody;
    }
}
