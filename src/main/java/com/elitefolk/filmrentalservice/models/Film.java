package com.elitefolk.filmrentalservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;
    private String title;
    private String description;

    @Column(columnDefinition = "YEAR")
    private Integer releaseYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    private String specialFeatures;
    private Date lastUpdate;
}
