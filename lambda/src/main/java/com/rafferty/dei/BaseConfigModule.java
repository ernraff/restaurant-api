package com.rafferty.dei;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafferty.repo.RestaurantRepository;
import com.rafferty.service.RestaurantService;
import dagger.Module;
import dagger.Provides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Singleton;

@Module(includes = {DynamoDBModule.class, ObjectMapperModule.class})
public class BaseConfigModule {
    private static Logger LOG = LoggerFactory.getLogger(BaseConfigModule.class);

    @Provides
    @Singleton
    RestaurantRepository restaurantRepository(AmazonDynamoDB client) {
        LOG.info("Restaurant repository initializing...");
        RestaurantRepository restaurantRepository = new RestaurantRepository(client);
        LOG.info("Restaurant repository initialization complete...");
        return restaurantRepository;
    }

    @Provides
    @Singleton
    RestaurantService restaurantService(RestaurantRepository repository, ObjectMapper objectMapper) {
        LOG.info("Restaurant service initializing...");
        RestaurantService restaurantService = new RestaurantService(repository, objectMapper);
        LOG.info("Restaurant service initialization complete...");
        return restaurantService;
    }
}
