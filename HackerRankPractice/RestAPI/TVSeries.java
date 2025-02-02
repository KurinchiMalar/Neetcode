package HackerRankPractice.RestAPI;
/*
USe the HTTP GET method to retrieve information about recent television shows.,. Query : https://jsonmock.hackerrank.com/api/tvseries to ind all the shows in a genre. The query result is paginated., To access additional pages, append ? page={num} to the URL where um is the page number.
The repsonse is a JSON object with the following 5 fiedls.:
page: current page of the results
per_page : maximum number of results returned per page
total: total number or results
total_pages: total number of pages iwth results
data : an arry of tv series records

In data, each tv series has the following schema
name:(String)
runtime_of_series:years with a new season
certificate:rating
runtime_of_episodes
genre
imdb_rating
overview
no_of_votes
id: unique_id

Given a gnere, find the series with the highest imdb_rating. If there is a tie, return the alphabetically lower name.

public static String bestInGenre(String genre){}

Please give the code

 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TVSeries {

    public static String bestInGenre(String genre) {
        String baseURL = "https://jsonmock.hackerrank.com/api/tvseries";
        String bestSeries = "";
        double highestRating = -1.0;

        int currentPage = 1;  // Starting from page 1
        boolean hasNextPage = true;

        while (hasNextPage) {
            try {
                // Construct the URL for the current page
                String urlString = baseURL + "?page=" + currentPage;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray tvSeriesArray = jsonResponse.getJSONArray("data");
                int totalPages = jsonResponse.getInt("total_pages");

                // Check if the data array is empty
                if (tvSeriesArray.length() == 0) {
                    System.out.println("No data found on page " + currentPage);
                }

                // Iterate through the series in the current page
                for (int i = 0; i < tvSeriesArray.length(); i++) {
                    JSONObject series = tvSeriesArray.getJSONObject(i);

                    // Check if 'name' and 'genre' fields exist
                    if (series.has("name") && series.has("genre")) {
                        String name = series.getString("name");
                        String seriesGenre = series.getString("genre");
                        double imdbRating = series.getDouble("imdb_rating");

                        // Debugging: print out name, genre, and rating
                        System.out.println("Name: " + name + ", Genre: " + seriesGenre + ", IMDb Rating: " + imdbRating);

                        // Check if the input genre is part of the series' genre list
                        if (seriesGenre.toLowerCase().contains(genre.toLowerCase())) {
                            // Update the best series if the rating is higher or equal but alphabetically lower
                            if (imdbRating > highestRating || (imdbRating == highestRating && name.compareTo(bestSeries) < 0)) {
                                highestRating = imdbRating;
                                bestSeries = name;
                            }
                        }
                    } else {
                        // Print a message if 'name' or 'genre' are missing
                        System.out.println("Missing 'name' or 'genre' for series at index " + i);
                    }
                }

                // Move to the next page if there are more pages
                hasNextPage = currentPage < totalPages;
                currentPage++;  // Increment to go to the next page
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        return bestSeries;
    }

    public static void main(String[] args) {
        String genre = "Action"; // Input genre
        String result = bestInGenre(genre);
        if (result.isEmpty()) {
            System.out.println("No series found for genre: " + genre);
        } else {
            System.out.println("Best series in genre '" + genre + "': " + result);
        }
    }
}

