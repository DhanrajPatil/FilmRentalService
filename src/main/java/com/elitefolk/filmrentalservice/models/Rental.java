package com.elitefolk.filmrentalservice.models;

import com.elitefolk.filmrentalservice.dtos.RentalDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rental")
@EnableJpaAuditing
@SqlResultSetMapping(
        name = "RentalDtoMapping",
        classes = @ConstructorResult(
                targetClass = RentalDto.class,
                columns = {
                        @ColumnResult(name = "rentalId", type = Integer.class),
                        @ColumnResult(name = "inventoryId", type = Integer.class),
                        @ColumnResult(name = "filmId", type = Short.class),
                        @ColumnResult(name = "filmTitle", type = String.class),
                        @ColumnResult(name = "releaseYear", type = Integer.class),
                        @ColumnResult(name = "rentalRate", type = Double.class),
                        @ColumnResult(name = "rentalDate", type = LocalDate.class),
                        @ColumnResult(name = "agreedReturnDate", type = LocalDate.class),
                        @ColumnResult(name = "returnDate", type = LocalDate.class),
                        @ColumnResult(name = "customerId", type = Short.class),
                        @ColumnResult(name = "customerName", type = String.class),
                        @ColumnResult(name = "customerEmail", type = String.class),
                        @ColumnResult(name = "staffId", type = Byte.class),
                        @ColumnResult(name = "staffName", type = String.class),
                        @ColumnResult(name = "staffEmail", type = String.class),
                }
        )
)
@NamedStoredProcedureQuery(
        name = "Rental.getAllRentalsByFilm",
        procedureName = "get_rentals_by_film_Id",
        parameters = {
                @StoredProcedureParameter(name = "filmId", type = Short.class, mode = ParameterMode.IN)
        },
        resultSetMappings = {"RentalDtoMapping"}
)
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private LocalDate rentalDate;

    private LocalDate returnDate;

    @Column(nullable = false)
    private LocalDate agreedReturnDate;

    @ManyToOne()
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @UpdateTimestamp
    private Timestamp lastUpdate;
}
