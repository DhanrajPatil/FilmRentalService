package com.elitefolk.filmrentalservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "address")
@EnableJpaAuditing
public class Address {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Short id;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(length = 50)
    private String address2;

    @Column(nullable = false, length = 30)
    private String district;

    @Column(nullable = false, length = 30)
    private String postalCode;

    @Column(nullable = false)
    private String phone;

    @UpdateTimestamp
    private java.sql.Timestamp lastUpdate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(columnDefinition = "geometry") // Ensure the column type is "geometry"
    //@Type(org.hibernate.spatial.JTSGeometryType.class) // Correct Hibernate Spatial type
    private Geometry location;

}
