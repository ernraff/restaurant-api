package com.rafferty.dei;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafferty.repo.RestaurantRepository;
import com.rafferty.service.RestaurantService;
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
public final class BaseConfigModule_RestaurantServiceFactory implements Factory<RestaurantService> {
  private final BaseConfigModule module;

  private final Provider<RestaurantRepository> repositoryProvider;

  private final Provider<ObjectMapper> objectMapperProvider;

  public BaseConfigModule_RestaurantServiceFactory(BaseConfigModule module,
      Provider<RestaurantRepository> repositoryProvider,
      Provider<ObjectMapper> objectMapperProvider) {
    this.module = module;
    this.repositoryProvider = repositoryProvider;
    this.objectMapperProvider = objectMapperProvider;
  }

  @Override
  public RestaurantService get() {
    return restaurantService(module, repositoryProvider.get(), objectMapperProvider.get());
  }

  public static BaseConfigModule_RestaurantServiceFactory create(BaseConfigModule module,
      Provider<RestaurantRepository> repositoryProvider,
      Provider<ObjectMapper> objectMapperProvider) {
    return new BaseConfigModule_RestaurantServiceFactory(module, repositoryProvider, objectMapperProvider);
  }

  public static RestaurantService restaurantService(BaseConfigModule instance,
      RestaurantRepository repository, ObjectMapper objectMapper) {
    return Preconditions.checkNotNullFromProvides(instance.restaurantService(repository, objectMapper));
  }
}
