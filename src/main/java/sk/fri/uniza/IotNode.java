package sk.fri.uniza;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sk.fri.uniza.api.WeatherStationService;
import sk.fri.uniza.model.WeatherData;

import java.io.IOException;
import java.util.List;

public class IotNode {
    private final Retrofit retrofit;
    private final WeatherStationService weatherStationService;

    public IotNode() {

        retrofit = new Retrofit.Builder()
                // Url adresa kde je umietnená WeatherStation služba
                .baseUrl("http://localhost:9000/")
                // Na konvertovanie JSON objektu na java POJO použijeme
                // Jackson knižnicu
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        // Vytvorenie inštancie komunikačného rozhrania
        weatherStationService = retrofit.create(WeatherStationService.class);

    }

    public WeatherStationService getWeatherStationService() {
        return weatherStationService;
    }

    public double getAverageTemperature(String station, String from, String to) throws Exception {
        Call<List<WeatherData>> historyWeatherFieldsPojo =
                this.getWeatherStationService().getHistoryWeather(station, from, to);

        double sum = 0.0;
        List<WeatherData> body = null;

        try {
            Response<List<WeatherData>> response = historyWeatherFieldsPojo.execute();

            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme inštancie triedy WeatherData
                body = response.body();

                for (WeatherData data : body) {
                    sum += data.getAirTemperature();
                }

            } else {
                throw new Exception(response.errorBody().toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum / body.size();
    }

}
