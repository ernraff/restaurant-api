package com.rafferty.dei;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import dagger.Module;
import dagger.Provides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Module public class DynamoDBModule {
    private static Logger LOG = LoggerFactory.getLogger(DynamoDBModule.class);
    @Provides
    @Singleton
    AmazonDynamoDB provideAmazonDynamoDB() {
        LOG.info("Initializing DynamoDB client...");
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        LOG.info("DynamoDB client initialized!");
        return client;
    }
}