package com.rafferty.dei;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.rafferty.repo.RestaurantRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class BaseConfigModule_RestaurantRepositoryFactory implements Factory<RestaurantRepository> {
  private final BaseConfigModule module;

  private final Provider<AmazonDynamoDB> clientProvider;

  public BaseConfigModule_RestaurantRepositoryFactory(BaseConfigModule module,
      Provider<AmazonDynamoDB> clientProvider) {
    this.module = module;
    this.clientProvider = clientProvider;
  }

  @Override
  public RestaurantRepository get() {
    return restaurantRepository(module, clientProvider.get());
  }

  public static BaseConfigModule_RestaurantRepositoryFactory create(BaseConfigModule module,
      Provider<AmazonDynamoDB> clientProvider) {
    return new BaseConfigModule_RestaurantRepositoryFactory(module, clientProvider);
  }

  public static RestaurantRepository restaurantRepository(BaseConfigModule instance,
      AmazonDynamoDB client) {
    return Preconditions.checkNotNullFromProvides(instance.restaurantRepository(client));
  }
}
