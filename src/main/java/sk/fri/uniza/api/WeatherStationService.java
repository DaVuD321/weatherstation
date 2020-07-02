package sk.fri.uniza.api;

import retrofit2.Call;
import retrofit2.http.*;
import sk.fri.uniza.model.Location;
import sk.fri.uniza.model.Token;
import sk.fri.uniza.model.WeatherData;

import java.util.List;
import java.util.Map;


public interface WeatherStationService {

    // ... getCurrentWeatherAsMap(station);


    // ... getCurrentWeatherAsMap(station, fields);


    // ... getStationLocations();


    // ... getCurrentWeather(station);


    // ... getCurrentWeather(station, fields);


    // ... getHistoryWeather(station, from, to);


    // ... getHistoryWeather(  station, from, to, fields);

    // ... getToken(authorization, claims);


    // ... getStationLocationsAuth(authorization);


    // ... getCurrentWeatherAuth(authorization, station);


    // ... getCurrentWeatherAuth(authorization, station, fields);


    // ... getHistoryWeatherAuth(authorization, station, from, to);


    // ... getHistoryWeatherAuth(authorization, station, from, to, fields);

}
