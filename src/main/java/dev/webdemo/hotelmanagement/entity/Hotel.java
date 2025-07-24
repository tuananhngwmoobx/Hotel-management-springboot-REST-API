package dev.webdemo.hotelmanagement.entity;

import lombok.Data;

@Data
// Annotation to generate getters, setters, toString, equals, and hashCode methods
public class Hotel {
    private String hotelId;

    private String setRoomName;

    private boolean status = true; // Default status is true (active)

    private Integer rate;
}
