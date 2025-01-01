package com.elitefolk.filmrentalservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "country")
@EnableJpaAuditing
public class Country {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short id;
    @Column(unique = true, nullable = false)
    private String country;
    @UpdateTimestamp
    private java.sql.Timestamp lastUpdate;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();
}
