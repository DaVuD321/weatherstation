package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Station Name",
        "Date",
        "Time",
        "Air Temperature",
        "Wet Bulb Temperature",
        "Humidity",
        "Rain Intensity",
        "Interval Rain",
        "Total Rain",
        "Precipitation Type",
        "Wind Direction",
        "Wind Speed",
        "Maximum Wind Speed",
        "Barometric Pressure",
        "Solar Radiation",
        "Heading",
        "Battery Life",
        "Measurement Timestamp Label"
})
public class WeatherData {
    @JsonProperty("Station Name")
    private String stationName;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Time")
    private String time;
    @JsonProperty("Air Temperature")
    private Double airTemperature;
    @JsonProperty("Wet Bulb Temperature")
    private Double wetBulbTemperature;
    @JsonProperty("Humidity")
    private Integer humidity;
    @JsonProperty("Rain Intensity")
    private Integer rainIntensity;
    @JsonProperty("Interval Rain")
    private Integer intervalRain;
    @JsonProperty("Total Rain")
    private Integer totalRain;
    @JsonProperty("Precipitation Type")
    private Integer precipitationType;
    @JsonProperty("Wind Direction")
    private Integer windDirection;
    @JsonProperty("Wind Speed")
    private Double windSpeed;
    @JsonProperty("Maximum Wind Speed")
    private Double maximumWindSpeed;
    @JsonProperty("Barometric Pressure")
    private Double barometricPressure;
    @JsonProperty("Solar Radiation")
    private Integer solarRadiation;
    @JsonProperty("Heading")
    private Integer heading;
    @JsonProperty("Battery Life")
    private Integer batteryLife;
    @JsonProperty("Measurement Timestamp Label")
    private String measurementTimestampLabel;

    @JsonProperty("Station Name")
    public String getStationName() {
        return stationName;
    }

    @JsonProperty("Station Name")
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("Time")
    public String getTime() {
        return time;
    }

    @JsonProperty("Time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("Air Temperature")
    public Double getAirTemperature() {
        return airTemperature;
    }

    @JsonProperty("Air Temperature")
    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    @JsonProperty("Wet Bulb Temperature")
    public Double getWetBulbTemperature() {
        return wetBulbTemperature;
    }

    @JsonProperty("Wet Bulb Temperature")
    public void setWetBulbTemperature(Double wetBulbTemperature) {
        this.wetBulbTemperature = wetBulbTemperature;
    }

    @JsonProperty("Humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonProperty("Humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("Rain Intensity")
    public Integer getRainIntensity() {
        return rainIntensity;
    }

    @JsonProperty("Rain Intensity")
    public void setRainIntensity(Integer rainIntensity) {
        this.rainIntensity = rainIntensity;
    }

    @JsonProperty("Interval Rain")
    public Integer getIntervalRain() {
        return intervalRain;
    }

    @JsonProperty("Interval Rain")
    public void setIntervalRain(Integer intervalRain) {
        this.intervalRain = intervalRain;
    }

    @JsonProperty("Total Rain")
    public Integer getTotalRain() {
        return totalRain;
    }

    @JsonProperty("Total Rain")
    public void setTotalRain(Integer totalRain) {
        this.totalRain = totalRain;
    }

    @JsonProperty("Precipitation Type")
    public Integer getPrecipitationType() {
        return precipitationType;
    }

    @JsonProperty("Precipitation Type")
    public void setPrecipitationType(Integer precipitationType) {
        this.precipitationType = precipitationType;
    }

    @JsonProperty("Wind Direction")
    public Integer getWindDirection() {
        return windDirection;
    }

    @JsonProperty("Wind Direction")
    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }

    @JsonProperty("Wind Speed")
    public Double getWindSpeed() {
        return windSpeed;
    }

    @JsonProperty("Wind Speed")
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @JsonProperty("Maximum Wind Speed")
    public Double getMaximumWindSpeed() {
        return maximumWindSpeed;
    }

    @JsonProperty("Maximum Wind Speed")
    public void setMaximumWindSpeed(Double maximumWindSpeed) {
        this.maximumWindSpeed = maximumWindSpeed;
    }

    @JsonProperty("Barometric Pressure")
    public Double getBarometricPressure() {
        return barometricPressure;
    }

    @JsonProperty("Barometric Pressure")
    public void setBarometricPressure(Double barometricPressure) {
        this.barometricPressure = barometricPressure;
    }

    @JsonProperty("Solar Radiation")
    public Integer getSolarRadiation() {
        return solarRadiation;
    }

    @JsonProperty("Solar Radiation")
    public void setSolarRadiation(Integer solarRadiation) {
        this.solarRadiation = solarRadiation;
    }

    @JsonProperty("Heading")
    public Integer getHeading() {
        return heading;
    }

    @JsonProperty("Heading")
    public void setHeading(Integer heading) {
        this.heading = heading;
    }

    @JsonProperty("Battery Life")
    public Integer getBatteryLife() {
        return batteryLife;
    }

    @JsonProperty("Battery Life")
    public void setBatteryLife(Integer batteryLife) {
        this.batteryLife = batteryLife;
    }

    @JsonProperty("Measurement Timestamp Label")
    public String getMeasurementTimestampLabel() {
        return measurementTimestampLabel;
    }

    @JsonProperty("Measurement Timestamp Label")
    public void setMeasurementTimestampLabel(String measurementTimestampLabel) {
        this.measurementTimestampLabel = measurementTimestampLabel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("stationName", stationName)
                .append("date", date).append("time", time)
                .append("airTemperature", airTemperature)
                .append("wetBulbTemperature", wetBulbTemperature)
                .append("humidity", humidity)
                .append("rainIntensity", rainIntensity)
                .append("intervalRain", intervalRain)
                .append("totalRain", totalRain)
                .append("precipitationType", precipitationType)
                .append("windDirection", windDirection)
                .append("windSpeed", windSpeed)
                .append("maximumWindSpeed", maximumWindSpeed)
                .append("barometricPressure", barometricPressure)
                .append("solarRadiation", solarRadiation)
                .append("heading", heading).append("batteryLife", batteryLife)
                .append("measurementTimestampLabel", measurementTimestampLabel)
                .toString();
    }

}