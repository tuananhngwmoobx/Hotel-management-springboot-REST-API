package dev.webdemo.hotelmanagement.dto;

import lombok.Data;

@Data
public class UpdateHotelRequest {
    private String getRoomName;

    private boolean status; // Chỉ cho update name và status nên chỉ hứng 2 giá trị này

}
