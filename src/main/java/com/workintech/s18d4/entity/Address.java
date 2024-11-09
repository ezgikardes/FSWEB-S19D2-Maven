package com.workintech.s18d4.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "address", schema = "fsweb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    @NotNull
    private String street;

    @Column(name = "no")
    @NotNull
    private Integer no;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "country")
    private String country;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "address")
    private Customer customer;
}
