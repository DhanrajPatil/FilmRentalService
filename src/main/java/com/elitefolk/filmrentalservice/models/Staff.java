package com.elitefolk.filmrentalservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "staff")
@EnableJpaAuditing
public class Staff {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "store_id")
    private Store store;

    private boolean active;

    @Column(nullable = false, updatable = false, length = 50)
    private String username;

    @Column(length = 40)
    private String password;

    @Lob
    private Byte[] image;

    @UpdateTimestamp
    private java.sql.Timestamp lastUpdate;

}
