package dev.webdemo.hotelmanagement.controller;

import dev.webdemo.hotelmanagement.dto.CreateHotelRequest;
import dev.webdemo.hotelmanagement.dto.ResponseDTO;
import dev.webdemo.hotelmanagement.dto.UpdateHotelRequest;
import dev.webdemo.hotelmanagement.entity.Hotel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController // chỉ ra rằng những thằng phía dưới là REST API
@RequestMapping("/api/v1/hotels") // prefix path cho tất cả các API bên dưới

public class HotelController {

    private static List<Hotel> hotels = new ArrayList<Hotel>(); // các instance của cùng 1 class sẽ sử dụng chung gtri của biến hotels

    // 1. Create a new hotel
    // Method POST
    // URL: /api/v1/hotels
    @PostMapping // đánh dấu là API map với cái path trên Postman
    public Hotel createHotel(@RequestBody CreateHotelRequest request){

        Hotel hotel = new Hotel();

        hotel.setHotelId(request.getHotelId()); // nhờ lombok
        hotel.setHotelName(request.getHotelName()); // nhờ lombok
        hotel.setRate(request.getRate()); // nhờ lombok

        hotels.add(hotel);

        return hotel;
    }

    //2. Return hotels list
    //Method: GET
    // Path: api/v1/hotels

    @GetMapping
    public List<Hotel> getHotels(@RequestParam(required = false) Integer rate,
                                 @RequestParam(required = false) String status){
                                // param bắt buộc phải được truyền lên vì trên server, nếu ko sẽ tạo thành bad request
                                // Chỉnh lại các fields trong param required = false là ko bắt buộc phải truyền lên server tránh 400
                                // defaultValue là trường mặc định dạng String nếu như giá trị truyền vào là null

        // Với 2 params truyền vào có thể sinh ra 4 trường hợp

        // Case 1:
        // Chỉ có rate truyền vào
        if(rate != null && status == null){
            return findHotelByRate(rate);
        }

        // Case 2:
        // Chỉ có status truyền vào
        else if(status != null && rate == null){
            return findHotelByStatus(Boolean.parseBoolean(status));
        }

        // Case 3:
        // Cả 2 đều truyền vào
        else if(status != null && rate != null){
            return findHotelByStatusAndRate(rate, Boolean.parseBoolean(status));
        }

        // Case 4:
        //Không có param nào truyền vào
        else{
            return hotels; // Trả về toàn bộ danh sách khách sạn
        }
    }

    private List<Hotel> findHotelByRate(Integer rate){
        if(rate != null){

            List<Hotel> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
                // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
                // Save memory rather than ArrayList

            for (Hotel hotel : hotels){
                    if(hotel.getRate() == rate)
                        result.add(hotel);
            }

                return result;
        }

        return hotels;
    }

    private List<Hotel> findHotelByStatus(Boolean status){
        if(status != null){

            List<Hotel> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
                // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
                // Save memory rather than ArrayList

            for (Hotel hotel : hotels){
                    if(hotel.isStatus() == status)
                        result.add(hotel);
            }

                return result;
        }

        return hotels;
    }

    private List<Hotel> findHotelByStatusAndRate(Integer rate, Boolean status){
        if(status != null && rate != null){

            List<Hotel> result = new LinkedList<>(); // Lí do dùng LinkedList là vì ko cần tới thao tác truy xuất random
                // Đơn giản là chỉ cần thêm vào result các khách sạn có rate >= rate truyền vào
                // Save memory rather than ArrayList

            for (Hotel hotel : hotels){
                    if(hotel.isStatus() == status && hotel.getRate() == rate)
                        result.add(hotel);
            }

                return result;
        }

        return hotels;
    }

    //3. Get hotel by ID
    //Method : GET
    //Path: /api/v1/hotels/{hotelId}

    @GetMapping("/{hotelId}")
    public Hotel getHotelById(@PathVariable String hotelId){

        return findHotelById(hotelId);

    }

    //4. update hotel by ID
    // Method: PUT
    //path : /api/v1/hotels/{hotelId}
    @PutMapping("/{hotelId}")
    public Hotel updateHotelById(@PathVariable String hotelId,
                                 @RequestBody UpdateHotelRequest request){ // Cũng cần hứng những cái thông tin của người dùng để update lên

        // find the hotel and update
        Hotel hotel = findHotelById(hotelId);
        if(hotel == null){
            return null;
        }
        else {
            hotel.setHotelName(request.getHotelName());
            hotel.setStatus(request.isStatus());
            return hotel;
        }
    }

    //5. Disable hotel by ID
    // Method: DELETE
    //path: /api/v1/hotels/{hotelId}
    @DeleteMapping("/{hotelId}")
    public ResponseDTO disableHotel(@PathVariable String hotelId){

        Hotel hotel = findHotelById(hotelId);
        if(hotel != null){
            hotel.setStatus(false);
            return new ResponseDTO(true, "Hotel disabled successfully");
        }

        return new ResponseDTO(false, "Hotel not found");
    }

    private Hotel findHotelById(String hotelId){
        for (Hotel hotel : hotels){
            if(hotel.getHotelId().equals(hotelId)){
                return hotel;
            }
        }

        return null;
    }

}

