package com.ben.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private  int mrpPrice;

    private int sellingPrice;

    private int discountPercentage;

    private String description;

    private int quantity;

    private String color;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    private int numRating;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Seller seller;

    private LocalDateTime createdAt;

    private String size;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}
