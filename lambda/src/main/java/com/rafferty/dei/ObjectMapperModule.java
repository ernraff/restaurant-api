package com.rafferty.dei;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Module
public class ObjectMapperModule {
    private static Logger LOG = LoggerFactory.getLogger(ObjectMapperModule.class);
    @Provides
    @Singleton
    ObjectMapper objectMapper() {
        LOG.info("Object mapper initializing...");
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        LOG.info("Object mapper initialized!");
        return objectMapper;
    }
}
