package com.rafferty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafferty.repo.RestaurantRepository;
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
public final class RestaurantService_Factory implements Factory<RestaurantService> {
  private final Provider<RestaurantRepository> repositoryProvider;

  private final Provider<ObjectMapper> objectMapperProvider;

  public RestaurantService_Factory(Provider<RestaurantRepository> repositoryProvider,
      Provider<ObjectMapper> objectMapperProvider) {
    this.repositoryProvider = repositoryProvider;
    this.objectMapperProvider = objectMapperProvider;
  }

  @Override
  public RestaurantService get() {
    return newInstance(repositoryProvider.get(), objectMapperProvider.get());
  }

  public static RestaurantService_Factory create(Provider<RestaurantRepository> repositoryProvider,
      Provider<ObjectMapper> objectMapperProvider) {
    return new RestaurantService_Factory(repositoryProvider, objectMapperProvider);
  }

  public static RestaurantService newInstance(RestaurantRepository repository,
      ObjectMapper objectMapper) {
    return new RestaurantService(repository, objectMapper);
  }
}
