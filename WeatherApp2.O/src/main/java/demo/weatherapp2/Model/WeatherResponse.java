package demo.weatherapp2.Model;

import org.springframework.stereotype.Component;

@Component
public class WeatherResponse {
    private String temperature;
    private String weather_description;

    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getWeather_description() {
        return weather_description;
    }
    public void setWeather_description(String type) {
        this.weather_description = type;
    }
}
