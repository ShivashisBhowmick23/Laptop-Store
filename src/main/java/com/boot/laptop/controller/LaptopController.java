package com.boot.laptop.controller;

import com.boot.laptop.constant.URLConstant;
import com.boot.laptop.mapper.LaptopMapper;
import com.boot.laptop.model.Laptop;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.response.LaptopResponse;
import com.boot.laptop.service.LaptopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Add Laptop into the database", description = " addLaptop method will add into the database and return inserted Laptop", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<LaptopResponse> addLaptop(@RequestBody LaptopRequest laptopRequest) {
        Laptop request = laptopMapper.mapLaptopRequestToLaptop(laptopRequest);
        laptopService.addLaptop(request);
        //now we are taking laptop type request and using the mapper class we are converting the request into response
        LaptopResponse response = laptopMapper.mapLaptopToLaptopResponse(request);
        return ResponseEntity.ok(response);

    }

    @GetMapping(URLConstant.LAPTOP_COLLECTION)
    @Operation(summary = "Get all the laptops from the database", description = "getAllLaptop method will return all the laptops as list", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<List<LaptopResponse>> getAllLaptop() {
        List<Laptop> laptopList = laptopService.getAllLaptop();
        List<LaptopResponse> responseList = laptopMapper.mapLaptopListToLaptopResponseList(laptopList);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping(URLConstant.LAPTOP_BY_LAPTOP_ID)
    @Operation(summary = "Fetch Laptop By LaptopId", description = "getAllLaptop method will return all the laptops as list", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<LaptopResponse> getLaptopById(@PathVariable("laptop_id") int laptop_id) {
        laptopService.getLaptopById(laptop_id);
        LaptopResponse response = laptopMapper.mapLaptopToLaptopResponseByLaptopID(laptop_id);
        return ResponseEntity.ok(response);
    }

}
