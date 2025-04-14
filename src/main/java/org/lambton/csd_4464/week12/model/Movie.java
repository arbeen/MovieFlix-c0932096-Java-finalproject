package org.lambton.csd_4464.week12.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    private Long id;

    private String title;
    private String overview;
    private String poster_path;
    private String release_date;
    private double vote_average;
    private String backdrop_path;
    private String original_title;

}
