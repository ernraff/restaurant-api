package com.rafferty.dei;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafferty.repo.RestaurantRepository;
import com.rafferty.service.RestaurantService;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import javax.annotation.processing.Generated;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DaggerBaseConfig {
  private DaggerBaseConfig() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static BaseConfig create() {
    return new Builder().build();
  }

  public static final class Builder {
    private BaseConfigModule baseConfigModule;

    private DynamoDBModule dynamoDBModule;

    private ObjectMapperModule objectMapperModule;

    private Builder() {
    }

    public Builder baseConfigModule(BaseConfigModule baseConfigModule) {
      this.baseConfigModule = Preconditions.checkNotNull(baseConfigModule);
      return this;
    }

    public Builder dynamoDBModule(DynamoDBModule dynamoDBModule) {
      this.dynamoDBModule = Preconditions.checkNotNull(dynamoDBModule);
      return this;
    }

    public Builder objectMapperModule(ObjectMapperModule objectMapperModule) {
      this.objectMapperModule = Preconditions.checkNotNull(objectMapperModule);
      return this;
    }

    public BaseConfig build() {
      if (baseConfigModule == null) {
        this.baseConfigModule = new BaseConfigModule();
      }
      if (dynamoDBModule == null) {
        this.dynamoDBModule = new DynamoDBModule();
      }
      if (objectMapperModule == null) {
        this.objectMapperModule = new ObjectMapperModule();
      }
      return new BaseConfigImpl(baseConfigModule, dynamoDBModule, objectMapperModule);
    }
  }

  private static final class BaseConfigImpl implements BaseConfig {
    private final BaseConfigImpl baseConfigImpl = this;

    private Provider<AmazonDynamoDB> provideAmazonDynamoDBProvider;

    private Provider<RestaurantRepository> restaurantRepositoryProvider;

    private Provider<ObjectMapper> objectMapperProvider;

    private Provider<RestaurantService> restaurantServiceProvider;

    private BaseConfigImpl(BaseConfigModule baseConfigModuleParam,
        DynamoDBModule dynamoDBModuleParam, ObjectMapperModule objectMapperModuleParam) {

      initialize(baseConfigModuleParam, dynamoDBModuleParam, objectMapperModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final BaseConfigModule baseConfigModuleParam,
        final DynamoDBModule dynamoDBModuleParam,
        final ObjectMapperModule objectMapperModuleParam) {
      this.provideAmazonDynamoDBProvider = DoubleCheck.provider(DynamoDBModule_ProvideAmazonDynamoDBFactory.create(dynamoDBModuleParam));
      this.restaurantRepositoryProvider = DoubleCheck.provider(BaseConfigModule_RestaurantRepositoryFactory.create(baseConfigModuleParam, provideAmazonDynamoDBProvider));
      this.objectMapperProvider = DoubleCheck.provider(ObjectMapperModule_ObjectMapperFactory.create(objectMapperModuleParam));
      this.restaurantServiceProvider = DoubleCheck.provider(BaseConfigModule_RestaurantServiceFactory.create(baseConfigModuleParam, restaurantRepositoryProvider, objectMapperProvider));
    }

    @Override
    public RestaurantService getRestaurantService() {
      return restaurantServiceProvider.get();
    }
  }
}
