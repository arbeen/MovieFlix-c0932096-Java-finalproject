package org.lambton.csd_4464.week12.repository;

import org.lambton.csd_4464.week12.model.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
}
