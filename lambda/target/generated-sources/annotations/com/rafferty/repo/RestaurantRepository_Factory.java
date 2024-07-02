package com.rafferty.repo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class RestaurantRepository_Factory implements Factory<RestaurantRepository> {
  private final Provider<AmazonDynamoDB> clientProvider;

  public RestaurantRepository_Factory(Provider<AmazonDynamoDB> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public RestaurantRepository get() {
    return newInstance(clientProvider.get());
  }

  public static RestaurantRepository_Factory create(Provider<AmazonDynamoDB> clientProvider) {
    return new RestaurantRepository_Factory(clientProvider);
  }

  public static RestaurantRepository newInstance(AmazonDynamoDB client) {
    return new RestaurantRepository(client);
  }
}
