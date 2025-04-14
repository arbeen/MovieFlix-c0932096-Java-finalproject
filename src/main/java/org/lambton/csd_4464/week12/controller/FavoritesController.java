package org.lambton.csd_4464.week12.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.lambton.csd_4464.week12.service.MovieService;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    private final MovieService movieService;

    public FavoritesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String showFavorites(Model model) {
        model.addAttribute("favorites", movieService.getFavoriteMovies());
        return "favorites";
    }

    @PostMapping("/add/{id}")
    public String addFavorite(@PathVariable Long id) {
        movieService.addToFavorites(id);
        return "redirect:/favorites";
    }

    @GetMapping("/remove/{id}")
    public String removeFromFavorites(@PathVariable Long id) {
        movieService.removeFromFavorites(id);
        return "redirect:/";
    }
}
