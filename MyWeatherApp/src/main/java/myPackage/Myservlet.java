package myPackage;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;




/**
 * Servlet implementation class Myservlet
 */
public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Myservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//API setup
		String apikey ="42da6a2d0d23f004dcca1889c66bbeaf";
		
		//Get the city from the form input
		String city =request.getParameter("city");
		
		//create the url form the openWeatherMap ApI request
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apikey;
		
		 //API integeration
		URL url=new URL(apiUrl);
		
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        //READING THE DATA FROM NETWORK
        InputStream inputStream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
       
        //WANT TO STORE IN STRING
        StringBuilder responseContent = new StringBuilder();
        
        //input lene ke liyee the reader , will create scanner class
         Scanner scanner = new Scanner(reader);
         while(scanner.hasNext()) {
        	 responseContent.append(scanner.nextLine());
         }
         scanner.close();
         
         //type casting = parsing the data into json
         Gson gson=new Gson();
         JsonObject jsonObject= gson.fromJson(responseContent.toString(),JsonObject.class);
         
         //Date & Time
         long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
         String date = new Date(dateTimestamp).toString();
         
         //Temperature
         double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
         int temperatureCelsius = (int) (temperatureKelvin - 273.15);
        
         //Humidity
         int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
         
         //Wind Speed
         double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
         
         //Weather Condition
         String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
         
         
         // Set the data as request attributes (for sending to the jsp page)
         request.setAttribute("date", date);
         request.setAttribute("city", city);
         request.setAttribute("temperature", temperatureCelsius);
         request.setAttribute("weathercondition",weatherCondition);
         request.setAttribute("humidity", humidity);
         request.setAttribute("windSpeed", windSpeed);
         request.setAttribute("weatherData", responseContent.toString());
         
         
         connection.disconnect();
         
         //forward the request and response to the weather jsp page for readering
         request.getRequestDispatcher("index.jsp").forward(request, response);
         
	}

}
