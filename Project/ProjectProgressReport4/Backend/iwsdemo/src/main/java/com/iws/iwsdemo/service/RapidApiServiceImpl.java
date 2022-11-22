package com.iws.iwsdemo.service;

import com.iws.iwsdemo.models.RestaurantByLoactionGetResponse;
import com.iws.iwsdemo.models.RestaurantDetailsGetByIdResponse;
import com.iws.iwsdemo.models.Restaurants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RapidApiServiceImpl implements RapidApiService {

  @Autowired
  public final RestTemplate restTemplate;

  public RapidApiServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<Restaurants> getRestaurantByLocation(String latitude, String longitude) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("X-RapidAPI-Host", "travel-advisor.p.rapidapi.com");
    httpHeaders.add("X-RapidAPI-Key", "a1fd5239b5mshcf67a6dfdeecc53p1af634jsn46d1a6819805");
    HttpEntity<HttpHeaders> entity = new HttpEntity<>(httpHeaders);

    ResponseEntity<RestaurantByLoactionGetResponse> response = restTemplate.exchange(
        "https://travel-advisor.p.rapidapi.com/restaurants/list-by-latlng?latitude=" + latitude
            + "&longitude=" + longitude
            + "&limit=30&currency=USD&distance=2&open_now=false&lunit=km&lang=en_US",
        HttpMethod.GET,
        entity,
        RestaurantByLoactionGetResponse.class);

    RestaurantByLoactionGetResponse restaurantByLoactionGetResponse = response.getBody();
    return restaurantByLoactionGetResponse.getData();
  }

  @Override
  public RestaurantDetailsGetByIdResponse getRestaurantDetailsById(String restaurantId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("X-RapidAPI-Host", "travel-advisor.p.rapidapi.com");
    httpHeaders.add("X-RapidAPI-Key", "f99e6502cbmshf3c67c121c57598p1be604jsnc35c14130368");
    HttpEntity<HttpHeaders> entity = new HttpEntity<>(httpHeaders);

    ResponseEntity<RestaurantDetailsGetByIdResponse> response = restTemplate.exchange(
        "https://travel-advisor.p.rapidapi.com/restaurants/get-details?location_id=" + restaurantId
            + "&currency=USD&lang=en_US",
        HttpMethod.GET,
        entity,
        RestaurantDetailsGetByIdResponse.class);

    return response.getBody();
  }
}
