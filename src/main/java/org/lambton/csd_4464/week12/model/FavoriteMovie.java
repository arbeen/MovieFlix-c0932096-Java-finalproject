package org.lambton.csd_4464.week12.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class FavoriteMovie {

    // Getters for the fields
    @Setter
    @Id
    private Long id;  // Movie's unique identifier, TMDb's movie ID

    @Setter
    private String title;
    private String overview;
    private String poster_path;



}
