package com.rafferty.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.rafferty.dei.DaggerBaseConfig;
import com.rafferty.model.ApiGatewayResponse;
import com.rafferty.service.RestaurantService;
import com.rafferty.dei.BaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;
import java.util.HashMap;

public class RestaurantHandler implements RequestHandler<Object, ApiGatewayResponse>{
    private static Logger LOG = LoggerFactory.getLogger(RestaurantHandler.class);
    static final BaseConfig baseConfig = DaggerBaseConfig.builder().build();
    @Inject
    RestaurantService restaurantService;

    public RestaurantHandler() {
        init();
    }

    protected void init() {
        LOG.info("init function called!");
        restaurantService = baseConfig.getRestaurantService();
        if (restaurantService != null) {
            LOG.info("Restaurant Service Initialized!");
        } else {
            LOG.error("Failed to initialize Restaurant Service!");
        }
    }

    @Override
    public  ApiGatewayResponse handleRequest(Object input, Context context) {
        String responseBody = null;

        if (restaurantService != null) {
            responseBody = restaurantService.getRestaurants(input);
            LOG.info("Got response body: " + responseBody);
        } else {
            responseBody = "Restaurant service is not initialized.";
            LOG.error(responseBody);
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("X-amazon-author", "Erin");
        headers.put("X-amazon-apiVersion", "v1");

        return new ApiGatewayResponse(responseBody, 200, headers, false);

    }

}

