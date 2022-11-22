package com.iws.iwsdemo.service;

import com.iws.iwsdemo.models.RestaurantDetailsGetByIdResponse;
import com.iws.iwsdemo.models.Restaurants;
import java.util.List;

public interface RapidApiService {

  List<Restaurants> getRestaurantByLocation(String latitude, String longitude);

  RestaurantDetailsGetByIdResponse getRestaurantDetailsById(String restaurantId);

}
