package com.elitefolk.filmrentalservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "city")
@EnableJpaAuditing
public class City {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Short id;

    @Column(name = "city", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;

    @UpdateTimestamp
    private java.sql.Timestamp lastUpdate;
}
