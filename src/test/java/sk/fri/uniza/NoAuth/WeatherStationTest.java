package sk.fri.uniza;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import sk.fri.uniza.model.Location;
import sk.fri.uniza.model.Token;
import sk.fri.uniza.model.WeatherData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherStationTest {
    private static final DateTimeFormatter timeFormat =
            DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter dateFormat =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Test, prijem JSON dát konvertovaných do mapy
     */
    @Test
    @Tag("step_1")
    @DisplayName("Test aktuálnosti dát o počasí")
    public void testCurrentData() {
        IotNode iotNode = new IotNode();
        Call<Map<String, String>> currentWeather =
                iotNode.getWeatherStationService()
                        .getCurrentWeatherAsMap("station_1");
        try {
            Response<Map<String, String>> response = currentWeather.execute();
            assertTrue(response.isSuccessful(),
                    "Dotaz na server bol neúspešný:" +
                            (response.errorBody() != null ?
                                    response.errorBody().string() : ""));
            Map<String, String> body = response.body();

            assertEquals(body.get("Time"), LocalTime.now().format(timeFormat));
            assertEquals(body.get("Date"), LocalDate.now().format(dateFormat));
            System.out.println(body);
        } catch (IOException e) {

            assertTrue(false, e.getMessage());
        }
    }

    /**
     * Test, zoznam všetkých meteo staníc
     */
    @Test
    @Tag("step_2")
    @DisplayName("Test načítania zoznamu meteo staníc")
    public void testListOfLocations() {
        IotNode iotNode = new IotNode();
        Call<List<Location>> stationLocations =
                iotNode.getWeatherStationService().getStationLocations();
        try {
            Response<List<Location>> response =
                    stationLocations.execute();
            assertTrue(response.isSuccessful(),
                    "Dotaz na server bol neúspešný:" +
                            (response.errorBody() != null ?
                                    response.errorBody().string() : ""));
            List<Location> body = response.body();
            assertEquals(body.get(0).getId(), "station_1");
            assertEquals(body.get(1).getId(), "station_2");
            assertEquals(body.get(2).getId(), "station_3");
            System.out.println(body);
        } catch (IOException e) {

            assertTrue(false, e.getMessage());
        }
    }

    /**
     * Test, prijem JSON dát konvertovaných do mapy
     */
    @Test
    @Tag("step_3")
    @DisplayName("Test filtrovania položiek")
    public void testCurrentDataFields() {
        IotNode iotNode = new IotNode();
        Call<Map<String, String>> currentWeather =
                iotNode.getWeatherStationService()
                        .getCurrentWeatherAsMap("station_1",
                                List.of("time", "date",
                                        "airTemperature"));
        try {
            Response<Map<String, String>> response = currentWeather.execute();
            assertTrue(response.isSuccessful(),
                    "Dotaz na server bol neúspešný:" +
                            (response.errorBody() != null ?
                                    response.errorBody().string() : ""));
            Map<String, String> body = response.body();

            assertEquals(LocalTime.now().format(timeFormat), body.get("Time"));
            assertEquals(LocalDate.now().format(dateFormat), body.get("Date"));
            assertTrue(body.containsKey("Air Temperature"));
            assertFalse(body.containsKey("Humidity"));
            assertTrue(body.size() == 3);
            System.out.println(body);
        } catch (IOException e) {

            assertTrue(false, e.getMessage());
        }
    }

    /**
     * Test, prijem JSON dát konvertovaných na objekt
     */
    @Test
    @Tag("step_4")
    @DisplayName("Test konvertovania JSON na objekt")
    public void testCurrentDataObject() {
        IotNode iotNode = new IotNode();
        Call<WeatherData> currentWeather =
                iotNode.getWeatherStationService()
                        .getCurrentWeather("station_1");
        try {
            Response<WeatherData> response = currentWeather.execute();
            assertTrue(response.isSuccessful(),
                    "Dotaz na server bol neúspešný:" +
                            (response.errorBody() != null ?
                                    response.errorBody().string() : ""));
            WeatherData body = response.body();

            assertEquals(LocalTime.now().format(timeFormat), body.getTime());
            assertEquals(LocalDate.now().format(dateFormat), body.getDate());
            System.out.println(body);
        } catch (IOException e) {

            assertTrue(false, e.getMessage());
        }
    }

    /**
     * Test, historických dát o pocǎsí
     */
    @Test
    @Tag("step_5")
    @DisplayName("Test načítania historie meteo dát")
    public void testHisotryDataObject() {
        IotNode iotNode = new IotNode();
        Call<List<WeatherData>> currentWeather =
                iotNode.getWeatherStationService()
                        .getHistoryWeather("station_1", "01/01/2020 00:00",
                                "02" +
                                        "/01/2020 00:00");
        try {
            Response<List<WeatherData>> response = currentWeather.execute();
            assertTrue(response.isSuccessful(),
                    "Dotaz na server bol neúspešný:" +
                            (response.errorBody() != null ?
                                    response.errorBody().string() : ""));
            List<WeatherData> body = response.body();
            LocalDateTime dateTime = LocalDateTime.of(2020, 01, 01, 0, 0);
            LocalDateTime stopDate = LocalDateTime.of(2020, 01, 02, 0, 0);

            for (WeatherData weatherData : body) {
                LocalTime localTime =
                        LocalTime.parse(weatherData.getTime(), timeFormat);
                LocalDate localDate =
                        LocalDate.parse(weatherData.getDate(), dateFormat);
                assertEquals(dateTime, LocalDateTime.of(localDate, localTime));
                dateTime = dateTime.plusHours(1);
            }
            dateTime = dateTime.minusHours(1);
            assertEquals(stopDate, dateTime);

            System.out.println(body);
        } catch (IOException e) {

            assertTrue(false, e.getMessage());
        }
    }

}
