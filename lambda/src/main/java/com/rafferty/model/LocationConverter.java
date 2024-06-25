package com.rafferty.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationConverter implements DynamoDBTypeConverter<String, Location> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public String convert(Location object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Location unconvert(String object) {
        try {
            return MAPPER.readValue(object, Location.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
