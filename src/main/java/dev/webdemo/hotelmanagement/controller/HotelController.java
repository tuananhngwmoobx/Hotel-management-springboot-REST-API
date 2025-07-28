package dev.webdemo.hotelmanagement.controller;

import dev.webdemo.hotelmanagement.dto.CreateHotelRequest;
import dev.webdemo.hotelmanagement.dto.ResponseDTO;
import dev.webdemo.hotelmanagement.dto.UpdateHotelRequest;
import dev.webdemo.hotelmanagement.entity.Hotel;
import dev.webdemo.hotelmanagement.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController // chỉ ra rằng những thằng phía dưới là REST API
@RequestMapping("/api/v1/hotels") // prefix path cho tất cả các API bên dưới

public class HotelController {

    private static List<Hotel> hotels = new ArrayList<Hotel>(); // các instance của cùng 1 class sẽ sử dụng chung gtri của biến hotels

    @Autowired
    HotelService hotelService;


    // 1. Create a new hotel
    // Method POST
    // URL: /api/v1/hotels
    @PostMapping // đánh dấu là API map với cái path trên Postman
    public Hotel createHotel(@RequestBody CreateHotelRequest request) {
        return hotelService.createHotel(request);
    }


//    //2. Return hotels list
//    //Method: GET
//    // Path: api/v1/hotels

    @GetMapping
    public List<Hotel> getHotels(@RequestParam(required = false) Integer rate,
                                 @RequestParam(required = false) String status) {
        // param bắt buộc phải được truyền lên vì trên server, nếu ko sẽ tạo thành bad request
        // Chỉnh lại các fields trong param required = false là ko bắt buộc phải truyền lên server tránh 400
        // defaultValue là trường mặc định dạng String nếu như giá trị truyền vào là null

        return hotelService.getAll();
    }


//    private List<Hotel> findHotelByRate(Integer rate){
//        if(rate != null){
//
//            List<Hotel> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
//                // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
//                // Save memory rather than ArrayList
//
//            for (Hotel hotel : hotels){
//                    if(hotel.getRate() == rate)
//                        result.add(hotel);
//            }
//
//                return result;
//        }
//
//        return hotels;
//    }
//
//    private List<Hotel> findHotelByStatus(Boolean status){
//        if(status != null){
//
//            List<Hotel> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
//                // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
//                // Save memory rather than ArrayList
//
//            for (Hotel hotel : hotels){
//                    if(hotel.isStatus() == status)
//                        result.add(hotel);
//            }
//
//                return result;
//        }
//
//        return hotels;
//    }
//
//    private List<Hotel> findHotelByStatusAndRate(Integer rate, Boolean status){
//        if(status != null && rate != null){
//
//            List<Hotel> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
//                // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
//                // Save memory rather than ArrayList
//
//            for (Hotel hotel : hotels){
//                    if(hotel.isStatus() == status && hotel.getRate() == rate)
//                        result.add(hotel);
//            }
//
//                return result;
//        }
//
//        return hotels;
//    }

    //3. Get hotel by ID
    //Method : GET
    //Path: /api/v1/hotels/{hotelId}

    @GetMapping("/{hotelId}")
    public Hotel getHotelById(@PathVariable String hotelId) {

        return hotelService.getHotelById(Long.parseLong(hotelId));

    }

    //4. update hotel by ID
    // Method: PUT
    //path : /api/v1/hotels/{hotelId}
    @PutMapping("/{hotelId}")
    public Hotel updateHotelById(@PathVariable String hotelId,
                                 @RequestBody UpdateHotelRequest request) { // Cũng cần hứng những cái thông tin của người dùng để update lên

        return hotelService.updateHotel(Long.parseLong(hotelId), request);
    }

    //
//    //5. Disable hotel by ID
//    // Method: DELETE
//    //path: /api/v1/hotels/{hotelId}
    @DeleteMapping("/{hotelId}")
    public ResponseDTO disableHotel(@PathVariable Long hotelId) {
        return hotelService.disableHotel(hotelId);
    }
}

