package org.lambton.csd_4464.week12.repository;

import org.lambton.csd_4464.week12.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
