package com.elitefolk.filmrentalservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "actor")
@EnableJpaAuditing
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(nullable = false, length = 30)
    private String firstName;

    @Column(nullable = false,length = 30)
    private String lastName;

    @ManyToMany(mappedBy = "actors", fetch = FetchType.LAZY)
    private List<Film> movies = new ArrayList<>();

    @UpdateTimestamp
    private java.sql.Timestamp lastUpdate;
}
