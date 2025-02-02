package HackerRankPractice.RestAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Restaurant {
    public String name;
    public String cuisine;
    public double rating;
    public List<String> tags;
}


public class RestaurantFilter{

    private static final String API_URL = "https://api.example.com/restaurants";

    public static List<Restaurant> getFilteredRestaurants(double minRating, String requiredTag){

		/*
			HttpClient
			HttpRequest
			HttpResponse
		*/
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // use jackson to parse the response
            ObjectMapper mapper = new ObjectMapper();
            List<Restaurant> restaurants = mapper.readValue(response.body(),new TypeReference<List<Restaurant>>(){});

            return restaurants.stream()
                    .filter(r -> r.rating >= minRating && r.tags.contains(requiredTag))
                    .collect(Collectors.toList());

        }catch (InterruptedException | IOException e) {
            System.out.println("Error fetching data: " + e.getMessage());
            return new ArrayList<>();
        }


    }
    public static void main(String[] args) {
        List<Restaurant> result = getFilteredRestaurants(4.5, "vegan");
        result.forEach(r -> System.out.println(r.name + " - " + r.rating));
    }

}
