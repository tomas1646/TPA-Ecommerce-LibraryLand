package com.libraryland.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@JsonIgnoreProperties(value = {"cartDetails", "orderDetails"})
public class Book extends Base {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "synopsis", length = 2000, nullable = false)
    private String synopsis;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "price", precision = 8, scale = 2, nullable = false)
    private float price;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "fk_book"),
            inverseJoinColumns = @JoinColumn(name = "fk_genre")
    )
    private List<Genre> genres = new ArrayList();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "fk_book"),
            inverseJoinColumns = @JoinColumn(name = "fk_author")
    )
    private List<Author> authors = new ArrayList();

    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE)
    //cascade type dudoso. Revisar. Que pas acon las ordenes cuando se borra el book?
    private List<CartDetail> cartDetails = new ArrayList<CartDetail>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE) //cascade type dudoso. Revisar
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
}
