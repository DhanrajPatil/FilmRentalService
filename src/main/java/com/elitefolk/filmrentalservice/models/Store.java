package com.elitefolk.filmrentalservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "store")
@EnableJpaAuditing
public class Store {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Byte id;

    @OneToOne(cascade = jakarta.persistence.CascadeType.PERSIST)
    @JoinColumn(name = "manager_staff_id", unique = true)
    private Staff manager;

    @OneToOne(cascade = jakarta.persistence.CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    private Double penaltyPerDay;

    @UpdateTimestamp
    private java.sql.Timestamp lastUpdate;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Staff> staff;
}
