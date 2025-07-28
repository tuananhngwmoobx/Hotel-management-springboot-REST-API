package dev.webdemo.hotelmanagement.repository;

import dev.webdemo.hotelmanagement.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface HotelRepository extends JpaRepository<Hotel, Long> { // Long là datatype của primary key

    Hotel findByHotelId(Long hotelId);
}