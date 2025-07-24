package dev.webdemo.hotelmanagement.dto;

import lombok.Data;

@Data
public class UpdateRoomRequest {

    private String roomId;

    private String roomName;

    private boolean status;
}
