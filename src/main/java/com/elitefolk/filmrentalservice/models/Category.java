package com.elitefolk.filmrentalservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;
    @Column(nullable = false, unique = true)
    private String name;
    @CreationTimestamp
    private Timestamp lastUpdate;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Film> films = new ArrayList<>();
}
