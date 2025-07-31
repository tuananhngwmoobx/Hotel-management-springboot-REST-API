package dev.webdemo.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hotel")
@Data // Already included No-arg constructor
// Annotation to generate getters, setters, toString, equals, and hashCode methods
public class Hotel {


    // Requiremens:
    //1. @Id - primary
    //2. No-arg constructor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long hotelId;

    @Column(name = "name")
    private String hotelName;

    @Column(name = "status") // ko có cũng đc do đã trùng với trong db
    private boolean status;

    @Transient  // nhưng attributes nào ko muốn lưu xuống DB
    private int rate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

//    public Hotel() {
//    }
}
