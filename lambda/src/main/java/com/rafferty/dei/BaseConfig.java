package com.rafferty.dei;

import com.rafferty.service.RestaurantService;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {BaseConfigModule.class})
public interface BaseConfig{
    RestaurantService getRestaurantService();
}