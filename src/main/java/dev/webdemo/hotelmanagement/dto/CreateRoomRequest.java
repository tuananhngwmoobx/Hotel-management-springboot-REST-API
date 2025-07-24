package dev.webdemo.hotelmanagement.dto;

import lombok.Data;

@Data
public class CreateRoomRequest {
    private String roomId;

    private String roomName;

    private boolean status;
}
