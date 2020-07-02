package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    @JsonProperty("id")
    private String id;
    @JsonProperty("country")
    private String country;
    @JsonProperty("address")
    private String address;
    @JsonProperty("town")
    private String town;
    @JsonProperty("gps")
    private String gps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", town='" + town + '\'' +
                ", gps='" + gps + '\'' +
                '}';
    }

}