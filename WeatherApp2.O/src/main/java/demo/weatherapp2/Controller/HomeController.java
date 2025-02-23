package demo.weatherapp2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.weatherapp2.Model.LocationRequest;
import demo.weatherapp2.Model.WeatherResponse;
import demo.weatherapp2.Service.MyService;



@Controller
public class HomeController {

    @Autowired
    private MyService myservice;

    @GetMapping("/")
    public String Home() {
        return "html/index.html";
    }

    @PostMapping("/tookweather")
    @ResponseBody
    public WeatherResponse postMethodName(@RequestBody LocationRequest request) {
        System.out.println("latitude "+request.getLatitude()+ " Longitude "+ request.getLongitude());
       return  myservice.fetchCity(request.getLatitude(),request.getLongitude());
    }
    
    
}
