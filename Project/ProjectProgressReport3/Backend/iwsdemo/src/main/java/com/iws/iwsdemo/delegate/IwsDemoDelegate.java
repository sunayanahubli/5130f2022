package com.iws.iwsdemo.delegate;

import com.iws.iwsdemo.models.FilterCriteria;
import com.iws.iwsdemo.models.KeyValuePair;
import com.iws.iwsdemo.models.Restaurants;
import com.iws.iwsdemo.service.RapidApiService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IwsDemoDelegate {

  @Autowired
  private final RapidApiService rapidApiService;

  public IwsDemoDelegate(RapidApiService rapidApiService) {
    this.rapidApiService = rapidApiService;
  }

  public List<Restaurants> getRestaurantsByLocation(String filter, String latitude,
      String longitude) {

    List<Restaurants> restaurantsList = rapidApiService
        .getRestaurantByLocation(latitude, longitude);

    if (filter.equalsIgnoreCase("All")) {
      return restaurantsList;
    } else {
      List<Restaurants> filteredList = new ArrayList<>();

      FilterCriteria filterCriteria = FilterCriteria.valueOf(filter);
      restaurantsList.forEach(restaurant -> {
        List<KeyValuePair> cuisines = restaurant.getCuisine();
        if (cuisines != null && !cuisines.isEmpty() && cuisines.stream().anyMatch(cuisine ->
            cuisine.getName().equalsIgnoreCase(filterCriteria.filterOption)
        )) {
          filteredList.add(restaurant);
        }
      });
      return filteredList;
    }
  }
}