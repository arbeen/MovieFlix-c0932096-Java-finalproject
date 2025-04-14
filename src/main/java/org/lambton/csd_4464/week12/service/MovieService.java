package org.lambton.csd_4464.week12.service;

import org.lambton.csd_4464.week12.model.FavoriteMovie;
import org.lambton.csd_4464.week12.repository.FavoriteMovieRepository;
import org.lambton.csd_4464.week12.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lambton.csd_4464.week12.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService{

    private final String API_KEY;
    private final String BASE_URL = "https://api.themoviedb.org/3";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    private FavoriteMovieRepository favoriteRepo;


    public MovieService(@Value("${tmdb.api.key}") String apiKey) {
        this.API_KEY = apiKey;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<Movie> getTrendingMovies() {
        String url = BASE_URL + "/trending/movie/week?api_key=" + API_KEY;
        String response = restTemplate.getForObject(url, String.class);
        List<Movie> movies = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode results = root.path("results");
            for (JsonNode result : results) {
                Movie movie = objectMapper.treeToValue(result, Movie.class);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    public Movie getMovieDetails(Long id) {
        String url = BASE_URL + "/movie/" + id + "?api_key=" + API_KEY;
        try {
            String response = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(response, Movie.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Movie> searchMovies(String query) {
        String url = BASE_URL + "/search/movie?api_key=" + API_KEY + "&query=" + query;
        String response = restTemplate.getForObject(url, String.class);
        List<Movie> movies = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode results = root.path("results");
            for (JsonNode result : results) {
                Movie movie = objectMapper.treeToValue(result, Movie.class);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }


    public void addToFavorites(Long id) {
        if (favoriteRepo.existsById(id)) return;

        Movie movie = getMovieDetails(id);
        System.out.println(movie);
        if (movie != null) {
            FavoriteMovie fav = new FavoriteMovie();
            fav.setId(movie.getId()); // TMDb ID as primary key (must not be null)
            fav.setTitle(movie.getTitle());
            fav.setOverview(movie.getOverview());
            fav.setPoster_path(movie.getPoster_path());
            // Set other fields
            favoriteRepo.save(fav); // ← will throw if movie.getId() is null
        }

    }

    public void removeFromFavorites(Long id) {
        favoriteRepo.deleteById(id);
    }

    public List<FavoriteMovie> getFavoriteMovies() {
        return favoriteRepo.findAll();
    }


}
