package com.elitefolk.filmrentalservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@Data
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
    @JoinColumn(name = "manager_staff_id")
    private Staff manager;

    @OneToOne(cascade = jakarta.persistence.CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    private Double chargesPerDay;

    @Column(nullable = false)
    private Double penaltyPerDay;

    @UpdateTimestamp
    private java.sql.Timestamp lastUpdate;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Staff> staff;
}
