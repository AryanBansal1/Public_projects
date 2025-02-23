package demo.weatherapp2.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.weatherapp2.Model.WeatherResponse;
@Service
public class MyService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherResponse weatherResponse;

     public WeatherResponse fetchCity(double latitude, double Longitude){
        String Api_key = "***"; 
        String Api_URL = "https://api.opencagedata.com/geocode/v1/json?q="+latitude+"+"+Longitude+ "&key="+Api_key;
        String response = restTemplate.getForObject(Api_URL, String.class);
        JSONObject jsonResponse = new JSONObject(response);     // Convert it into JSON Object

        // To extract city from the json resopnse
        JSONObject result = jsonResponse.getJSONArray("results")      // Get array "results" 
                                  .getJSONObject(0)             // Get first object of the array
                                  .getJSONObject("components");  //access "component" object
                                  
        String city1 =   result.optString("city","Unkown City"); // Get "City" (or default)
        System.out.println("The city is : "+city1);
        String state_district = result.optString("state_district", "normalized_city");  
        System.out.println("state_district : " +state_district );   // When city not available get the state_district
        String city ="";
        if(city1=="Unkown City"){
            city =state_district;
        }
        else{
            city=city1;
        }


        // call the api to get weather 
        String weather_api_key = "***";
        String weather_api_url = "http://api.weatherstack.com/current?access_key="+weather_api_key+"&query="+city;
        String weatherData = restTemplate.getForObject(weather_api_url, String.class);
        JSONObject weatherDataObject = new JSONObject(weatherData);

        // extract weather
        JSONObject CurrentweatherJSonObject = weatherDataObject.getJSONObject("current");

        // extract weather description
        String weather_description= CurrentweatherJSonObject.getJSONArray("weather_descriptions").optString(0,"No Description");
        String temperature = CurrentweatherJSonObject.optString("temperature","Tempertaure not detectable");
        System.out.println(weather_description+temperature);
        weatherResponse.setTemperature(temperature);
        weatherResponse.setWeather_description(weather_description);
        return weatherResponse;
    }
}
