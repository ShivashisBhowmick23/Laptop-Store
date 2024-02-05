package com.boot.laptop.controller;

import com.boot.laptop.mapper.LaptopMapper;
import com.boot.laptop.model.Laptop;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.response.LaptopResponse;
import com.boot.laptop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laptop-store")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;
    @Autowired
    private LaptopMapper laptopMapper;

    @PostMapping("/add-laptop")
    public ResponseEntity<LaptopResponse> addLaptop(@RequestBody LaptopRequest laptopRequest) {
        Laptop request = laptopMapper.mapLaptopRequestToLaptop(laptopRequest);
        laptopService.addLaptop(request);
        LaptopResponse response = laptopMapper.mapLaptopToLaptopResponse(request);
        return ResponseEntity.ok(response);

    }

}
