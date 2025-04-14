package org.lambton.csd_4464.week12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.lambton.csd_4464.week12.model.Movie;
import org.lambton.csd_4464.week12.service.MovieService;

import java.util.List;

@Controller
public class HomeController {

    private final MovieService movieService;

    @Autowired
    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Get the list of trending movies
        List<Movie> trendingMovies = movieService.getTrendingMovies();

        // Find the movie with the highest rating
        Movie highestRatedMovie = getHighestRatedMovie(trendingMovies);

        // Add movies and highest-rated movie to the model
        model.addAttribute("trendingMovies", trendingMovies);
        model.addAttribute("highlightMovie", highestRatedMovie);

        return "index"; // Render home.html
    }

    private Movie getHighestRatedMovie(List<Movie> movies) {
        return movies.stream()
                .max((movie1, movie2) -> Double.compare(movie1.getVote_average(), movie2.getVote_average()))
                .orElse(null); // Return null if no movies are available
    }
}
