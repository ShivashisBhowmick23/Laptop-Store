package com.boot.laptop.controller;

import com.boot.laptop.constant.URLConstant;
import com.boot.laptop.mapper.LaptopMapper;
import com.boot.laptop.model.Laptop;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.response.LaptopResponse;
import com.boot.laptop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLConstant.LAPTOP_STORE)
public class LaptopController {
    private final LaptopService laptopService;

    private final LaptopMapper laptopMapper;

    @Autowired
    public LaptopController(LaptopService laptopService, LaptopMapper laptopMapper) {
        this.laptopService = laptopService;
        this.laptopMapper = laptopMapper;
    }

    @PostMapping(URLConstant.ADD_LAPTOP_INTO_STORE)
    public ResponseEntity<LaptopResponse> addLaptop(@RequestBody LaptopRequest laptopRequest) {
        Laptop request = laptopMapper.mapLaptopRequestToLaptop(laptopRequest);
        laptopService.addLaptop(request);
        //now we are taking laptop type request and using the mapper class we are converting the request into response
        LaptopResponse response = laptopMapper.mapLaptopToLaptopResponse(request);
        return ResponseEntity.ok(response);

    }

    @GetMapping(URLConstant.LAPTOP_COLLECTION)
    public ResponseEntity<List<LaptopResponse>> getAllLaptop() {
        List<Laptop> laptopList = laptopService.getAllLaptop();
        List<LaptopResponse> responseList = laptopMapper.mapLaptopListToLaptopResponseList(laptopList);
        return ResponseEntity.ok(responseList);
    }

}
