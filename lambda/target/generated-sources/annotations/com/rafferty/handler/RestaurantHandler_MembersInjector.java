package com.rafferty.handler;

import com.rafferty.service.RestaurantService;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class RestaurantHandler_MembersInjector implements MembersInjector<RestaurantHandler> {
  private final Provider<RestaurantService> restaurantServiceProvider;

  public RestaurantHandler_MembersInjector(Provider<RestaurantService> restaurantServiceProvider) {
    this.restaurantServiceProvider = restaurantServiceProvider;
  }

  public static MembersInjector<RestaurantHandler> create(
      Provider<RestaurantService> restaurantServiceProvider) {
    return new RestaurantHandler_MembersInjector(restaurantServiceProvider);
  }

  @Override
  public void injectMembers(RestaurantHandler instance) {
    injectRestaurantService(instance, restaurantServiceProvider.get());
  }

  @InjectedFieldSignature("com.rafferty.handler.RestaurantHandler.restaurantService")
  public static void injectRestaurantService(RestaurantHandler instance,
      RestaurantService restaurantService) {
    instance.restaurantService = restaurantService;
  }
}
