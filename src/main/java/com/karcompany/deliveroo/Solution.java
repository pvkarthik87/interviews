package com.karcompany.deliveroo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Result {

    /*
     * Complete the 'bestInGenre' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING genre as parameter.
     *
     * Base URL: https://jsonmock.hackerrank.com/api/tvseries?page=
     */
    private static HttpClient httpClient = HttpClient.newHttpClient();

    public static String bestInGenre(String genre) {
        // Write your code here

        try {
            int pageCount = 0;
            long totalPages;
            List<Show> filteredShows = new ArrayList<>();
            do {
                GetShowsResult result = getShows(pageCount);
                totalPages = result.total_pages;
                pageCount++;

                result.shows
                        .stream()
                        .filter(item -> item.genre.contains(genre))
                        .forEach(filteredShows::add);

            } while (pageCount != totalPages);
            Optional<Show> result = filteredShows
                    .stream().min((o1, o2) -> {
                        if (o1.imdb_rating > o2.imdb_rating)
                            return -1;
                        if (o1.imdb_rating == o2.imdb_rating)
                            return o1.name.compareTo(o2.name);
                        return 0;
                    });

            if (result.isPresent()) {
                return result.get().name;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    private static GetShowsResult getShows(int pageNo) throws URISyntaxException, IOException, InterruptedException, ParseException {
        String pageUrl = "https://jsonmock.hackerrank.com/api/tvseries?page=" + pageNo;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(pageUrl))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JSONParser jsonParser = new JSONParser();
        Object parse = jsonParser.parse(response.body());
        return new GetShowsResult(
                (Long) ((JSONObject) parse).get("page"),
                (Long) ((JSONObject) parse).get("total_pages"),
                extractShows((JSONObject) parse)
        );
    }

    private static List<Show> extractShows(JSONObject parse) {
        JSONArray data = (JSONArray) parse.get("data");
        return data.stream()
                .map( item ->
                    new Show(
                            (String) ((JSONObject)item).get("name"),
                            (String) ((JSONObject)item).get("genre"),
                            getImdbRating((JSONObject) item)
                    )
                )
                .toList();
    }

    private static Double getImdbRating(JSONObject item) {
        Object imdbRating = item.get("imdb_rating");
        if (imdbRating instanceof Long) {
            return Double.valueOf((Long)imdbRating);
        }
        return (Double) imdbRating;
    }

}

class GetShowsResult {
    long page;
    long total_pages;
    List<Show> shows;

    public GetShowsResult(long page, long total_pages, List<Show> shows) {
        this.page = page;
        this.total_pages = total_pages;
        this.shows = shows;
    }
}

class Show {
    String name;
    String genre;
    double imdb_rating;

    public Show(String name, String genre, double imdb_rating) {
        this.name = name;
        this.genre = genre;
        this.imdb_rating = imdb_rating;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String genre = bufferedReader.readLine();
//
//        String result = Result.bestInGenre(genre);
//
//        bufferedWriter.write(result);
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
        String result = Result.bestInGenre("Animation");
        System.out.println(result);
    }
}
