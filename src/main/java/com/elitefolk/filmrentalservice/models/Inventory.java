package com.elitefolk.filmrentalservice.models;

import com.elitefolk.filmrentalservice.dtos.FilmAvailabilityDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "inventory")
@EnableJpaAuditing
@SqlResultSetMapping(
        name = "FilmAvailabilityMapping",
        classes = @ConstructorResult(
                targetClass = FilmAvailabilityDto.class,
                columns = {
                        @ColumnResult(name = "film_id", type = Short.class),
                        @ColumnResult(name = "Title", type = String.class),
                        @ColumnResult(name = "Release_Year", type = Integer.class),
                        @ColumnResult(name = "Rental_Rate", type = Double.class),
                        @ColumnResult(name = "Store_ID", type = Byte.class),
                        @ColumnResult(name = "AvailableCopies", type = Short.class)
                }
        )
)
@NamedStoredProcedureQuery(
        name = "Inventory.getFilmsWithInventoryAvailableCount",
        procedureName = "get_films_with_inventory_available_count",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "filmId", type = Short.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "storeId", type = Byte.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sizeOfPage", type = Short.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pageNo", type = Byte.class)
        },
        resultSetMappings = "FilmAvailabilityMapping"
)
public class Inventory {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @UpdateTimestamp
    private Timestamp lastUpdate;
}
