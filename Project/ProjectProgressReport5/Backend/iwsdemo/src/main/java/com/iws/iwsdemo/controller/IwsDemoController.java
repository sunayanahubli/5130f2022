package com.iws.iwsdemo.controller;

import com.iws.iwsdemo.delegate.IwsDemoDelegate;
import com.iws.iwsdemo.models.RestaurantDetailsGetByIdResponse;
import com.iws.iwsdemo.models.Restaurants;
import com.iws.iwsdemo.service.RapidApiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IwsDemoController {

  @Autowired
  private final RapidApiService rapidApiService;
  @Autowired
  private final IwsDemoDelegate iwsDemoDelegate;

  public IwsDemoController(RapidApiService rapidApiService,
      IwsDemoDelegate iwsDemoDelegate) {
    this.rapidApiService = rapidApiService;
    this.iwsDemoDelegate = iwsDemoDelegate;
  }

  @GetMapping(value = "/location")
  public List<Restaurants> getRestaurantByLocation(@Param("filter") String filter) {

    String latitude = "42.33229775411248";
    String longitude = "-71.63008028141098";
    return iwsDemoDelegate.getRestaurantsByLocation(filter, latitude, longitude);
  }

  @GetMapping(value = "/restaurantId/{locationId}")
  public RestaurantDetailsGetByIdResponse getRestaurantById(
      @PathVariable("locationId") String locationId) {

    return rapidApiService.getRestaurantDetailsById(locationId);
  }


}
