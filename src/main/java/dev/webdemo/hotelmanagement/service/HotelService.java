package dev.webdemo.hotelmanagement.service;

import dev.webdemo.hotelmanagement.dto.CreateHotelRequest;
import dev.webdemo.hotelmanagement.dto.ResponseDTO;
import dev.webdemo.hotelmanagement.dto.UpdateHotelRequest;
import dev.webdemo.hotelmanagement.entity.Hotel;
import dev.webdemo.hotelmanagement.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    //Ship 
    @Autowired // inject hotelRepository vao trong service
    HotelRepository hotelRepository;  // chưa implements interface mà vẫn khai báo đc giá trị này ??
    // Created in IoC container

    public Hotel getHotelById(Long hotelId){
        return hotelRepository.findByHotelId(hotelId);
    }

    public Hotel createHotel(CreateHotelRequest createHotelRequest){
        Hotel hotel = new Hotel();

        // Ko co khai bao hay khoi tao hotelId do no da tu tao trong entity
        hotel.setHotelName(createHotelRequest.getHotelName());
        hotel.setRate(createHotelRequest.getRate());
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAll(){
        return hotelRepository.findAll();
    }

    public Hotel updateHotel(Long hotelId, UpdateHotelRequest updateHotelRequest){
        Hotel hotel = hotelRepository.findByHotelId(hotelId);
        if(hotel == null){
            return null;
        }

        hotel.setHotelName(updateHotelRequest.getHotelName());
        hotel = hotelRepository.save(hotel); // UPSERT: update + insert: trong trường hợp ko có thì sẽ tạo mới

        return hotel;
    }

    public ResponseDTO disableHotel(Long hotelId){
        Hotel hotel = hotelRepository.findByHotelId(hotelId);
        if(hotel == null){
            return new ResponseDTO(false, "Hotel not found");
        }

        hotel.setStatus(false);
        hotel = hotelRepository.save(hotel);
//        hotelRepository.delete(hotel);
//        hotelRepository.deleteById(hotelId); hạn chế tối đa xóa data
        return new ResponseDTO(true, "Hotel disabled successfully");
    }
}
