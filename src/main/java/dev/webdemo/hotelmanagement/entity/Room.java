package dev.webdemo.hotelmanagement.entity;

import lombok.Data;

@Data
public class Room {

    private String roomId;

    private String roomName;

    private boolean status = true;
}
