const button = document.getElementById('button-weather')
function gotLocation(position){
    let langitute = position.coords.latitude
    let longitude = position.coords.longitude
    console.log(langitute+" "+longitude)

    // Call the backend API
    fetch('http://localhost:9999/tookweather',{ 
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'      // Inform the backend that we are sending JSON data
        },
        body: JSON.stringify({                      // convert js object into JSON format 
            latitude : langitute,   
            longitude : longitude
        })
    })
    .then(respone => respone.json())                // Convert the backend respose from JSON to js object
    .then(data => {                                 // used the recieved data to update the webpage
        console.log("Weather Data:", data); // Debugging: Print full response
        document.getElementById('para-for-weather').innerHTML = `Weather: ${data.weather_description}, Temperature: ${data.temperature}`     //update the p tag of html
        console.log(data.weather_description + " " + data.temperature);     
    })
    .catch(error => console.error("Error fetching weather",error))
}

function ErrorInLocation(){
    console.log("Failed to get the user Location")
}

button.addEventListener('click',async()=>{
    navigator.geolocation.getCurrentPosition(gotLocation,ErrorInLocation)
})