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
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherStationAuthTest {
    private static final String token =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                    ".eyJhbGwiOnRydWUsImlzcyI6ImF1" +
                    "dGgwIn0.Dc2RhFYE41g4Dh9baSuCv3o3JoYA8TlGMD4TlMKR2Jw";
    private static final DateTimeFormatter timeFormat =
            DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter dateFormat =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Získanie Api tókenu
     */
    @Test
    @Tag("step_6")
    @DisplayName("Získanie Api tókenu")
    public void testApiToken() {
        IotNode iotNode = new IotNode();
        Call<Token> tokenCall =
                iotNode.getWeatherStationService()
                        .getToken("Basic " + Base64.getEncoder()
                                        .encodeToString("admin:heslo".getBytes()),
                                List.of("all"));

        try {
            Response<Token> response = tokenCall.execute();
            assertTrue(response.isSuccessful(),
                    "Dotaz na server bol neúspešný:" +
                            (response.errorBody() != null ?
                                    response.errorBody().string() : ""));


            System.out.println(response.body().getToken());
        } catch (IOException e) {

            assertTrue(false, e.getMessage());
        }
    }

    /**
     * Test, zoznam všetkých meteo staníc
     */
    @Test
    @Tag("step_7")
    @DisplayName("Test načítania zoznamu meteo staníc")
    public void testListOfLocations() {
        IotNode iotNode = new IotNode();
        Call<List<Location>> stationLocations =
                iotNode.getWeatherStationService()
                        .getStationLocationsAuth(token);
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
     * Test, prijem JSON dát konvertovaných na objekt
     */
    @Test
    @Tag("step_9")
    @DisplayName("Test konvertovania JSON na objekt")
    public void testCurrentDataObject() {
        IotNode iotNode = new IotNode();
        Call<WeatherData> currentWeather =
                iotNode.getWeatherStationService()
                        .getCurrentWeatherAuth(token, "station_1");
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
    @Tag("step_10")
    @DisplayName("Test načítania historie meteo dát")
    public void testHisotryDataObject() {
        IotNode iotNode = new IotNode();
        Call<List<WeatherData>> currentWeather =
                iotNode.getWeatherStationService()
                        .getHistoryWeatherAuth(token, "station_1",
                                "01/01/2020 " +
                                        "00:00",
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
