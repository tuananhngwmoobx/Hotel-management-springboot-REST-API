package dev.webdemo.hotelmanagement.service;

import dev.webdemo.hotelmanagement.entity.Hotel;
import dev.webdemo.hotelmanagement.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired // inject hotelRepository vao trong service
    HotelRepository hotelRepository;  // chưa implements interface mà vẫn khai báo đc giá trị này ??

    public Hotel getHotelById(Long hotelId){
        return hotelRepository.findByHotelId(hotelId);
    }
}
