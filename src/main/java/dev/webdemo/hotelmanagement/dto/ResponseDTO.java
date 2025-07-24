package dev.webdemo.hotelmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Tạo constructor có tất cả các fields
public class ResponseDTO {
    private boolean status;
    private String message; // Thông báo kết quả xóa khách sạn


}
