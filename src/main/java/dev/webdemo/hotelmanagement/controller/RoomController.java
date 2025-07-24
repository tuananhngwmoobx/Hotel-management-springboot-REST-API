package dev.webdemo.hotelmanagement.controller;

import dev.webdemo.hotelmanagement.dto.CreateRoomRequest;
import dev.webdemo.hotelmanagement.dto.ResponseDTO;
import dev.webdemo.hotelmanagement.dto.UpdateHotelRequest;
import dev.webdemo.hotelmanagement.dto.UpdateRoomRequest;
import dev.webdemo.hotelmanagement.entity.Hotel;
import dev.webdemo.hotelmanagement.entity.Room;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private List<Room> rooms = new ArrayList<>();

    //1. Create room with RequestBody
    //  Method: POST
    @PostMapping
    public Room createRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        Room room = new Room();

        room.setRoomId(createRoomRequest.getRoomId());
        room.setRoomName(createRoomRequest.getRoomName());

        rooms.add(room);

        return room;
    }

    //2. Get room list
    // Method: GET
    @GetMapping
    public List<Room> getRooms(@RequestParam(required = false) Boolean status,
                               @RequestParam(required = false) String name){

        if(name != null && status == null){
            return findRoomByName(name);
        }

        // Case 2:
        // Chỉ có status truyền vào
        else if(status != null && name == null){
            return findRoomByStatus(status);
        }

        // Case 3:
        // Cả 2 đều truyền vào
        else if(status != null && name != null){
            return findRoomByStatusAndName(name, status);
        }

        // Case 4:
        //Không có param nào truyền vào
        else{
            return rooms; // Trả về toàn bộ danh sách khách sạn
        }
    }

    private List<Room> findRoomByName(String name){
        if(name != null){

            List<Room> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
            // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
            // Save memory rather than ArrayList

            for (Room room: rooms){
                if(room.getRoomName() == name)
                    result.add(room);
            }

            return result;
        }

        return rooms;
    }

    private List<Room> findRoomByStatus(Boolean status){
        if(status != null){

            List<Room> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
            // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
            // Save memory rather than ArrayList

            for (Room room : rooms){
                if(room.isStatus() == status)
                    result.add(room);
            }

            return result;
        }

        return rooms;
    }

    private List<Room> findRoomByStatusAndName(String name, Boolean status){
        if(status != null && name != null){

            List<Room> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
            // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
            // Save memory rather than ArrayList

            for (Room room : rooms){
                if(room.isStatus() == status && room.getRoomName() == name)
                    result.add(room);
            }

            return result;
        }

        return rooms;
    }

    //3. Get room by ID
    //Method : GET

    @GetMapping("/{roomId}")
    public Room getRoomById(@PathVariable String roomId){

        return findRoomById(roomId);

    }

    //4. update hotel by ID
    // Method: PUT
    //path : /api/v1/hotels/{hotelId}
    @PutMapping("/{roomId}")
    public Room updateHotelById(@PathVariable String roomId,
                                 @RequestBody UpdateRoomRequest request){ // Cũng cần hứng những cái thông tin của người dùng để update lên

        // find the room and update
        Room room = findRoomById(roomId);
        if(room == null){
            return null;
        }
        else {
            room.setRoomId(request.getRoomId());
            room.setRoomName(request.getRoomName());
            room.setStatus(request.isStatus());
            return room;
        }
    }

    //5. Disable room by ID
    // Method: DELETE

    @DeleteMapping("/{roomId}")
    public ResponseDTO disableRoom(@PathVariable String roomId){

        Room room = findRoomById(roomId);
        if(room != null){
            room.setStatus(false);
            return new ResponseDTO(true, "Room disabled successfully");
        }

        return new ResponseDTO(false, "Room not found");
    }

    private Room findRoomById(String roomId){
        for (Room room : rooms){
            if(room.getRoomId().equals(roomId)){
                return room;
            }
        }

        return null;
    }
}
