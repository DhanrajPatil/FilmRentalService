package com.elitefolk.filmrentalservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Timestamp;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "film")
@EnableJpaAuditing
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;
    @Column(nullable = false, length = 100)
    private String title;
    private String description;

    @Column(columnDefinition = "YEAR", nullable = false)
    private Integer releaseYear;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Column(length = 100)
    private String specialFeatures;
    @UpdateTimestamp
    private Timestamp lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = { "film_id", "actor_id" }))
    @JsonIgnoreProperties({ "movies" })
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnoreProperties({ "films" })
    private List<Category> categories = new ArrayList<>();
}
