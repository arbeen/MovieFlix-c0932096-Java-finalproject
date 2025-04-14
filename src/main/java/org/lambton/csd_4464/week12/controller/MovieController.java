package org.lambton.csd_4464.week12.controller;

import org.lambton.csd_4464.week12.model.Movie;
import org.lambton.csd_4464.week12.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public String home(Model model) {
        List<Movie> movies = movieService.getTrendingMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movie/{id}")
    public String getMovieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieDetails(id);
        model.addAttribute("movie", movie);
        return "movie-details";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Movie> movies = movieService.searchMovies(query);
        model.addAttribute("movies", movies);
        return "movies";
    }



}
