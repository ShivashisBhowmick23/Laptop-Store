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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(URLConstant.LAPTOP_STORE)
public class LaptopController {

    Logger LOGGER = LoggerFactory.getLogger(LaptopController.class);
    private final LaptopService laptopService;

    private final LaptopMapper laptopMapper;

    @Autowired
    public LaptopController(LaptopService laptopService, LaptopMapper laptopMapper) {
        this.laptopService = laptopService;
        this.laptopMapper = laptopMapper;
    }

    /**
     * Add Laptop into the database
     *
     * @param laptopRequest the request body containing the details of the laptop to be added
     * @return the response entity containing the inserted laptop
     */
    @PostMapping(URLConstant.ADD_LAPTOP_INTO_STORE)
    @Operation(
            summary = "Add Laptop into the database",
            description = "addLaptop method will add into the database and return inserted Laptop",
            method = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<LaptopResponse> addLaptop(@RequestBody LaptopRequest laptopRequest) {
        // Mapping laptop request to Laptop
        LOGGER.debug("Mapping laptop request to Laptop");
        Laptop request = laptopMapper.mapLaptopRequestToLaptop(laptopRequest);

        // Adding laptop to database
        LOGGER.debug("Adding laptop to database");
        laptopService.addLaptop(request);

        // Mapping laptop to laptop response
        LOGGER.debug("Mapping laptop to laptop response");
        LaptopResponse response = laptopMapper.mapLaptopToLaptopResponse(request);

        // Returning response with status OK
        LOGGER.debug("Returning response with status OK");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping(URLConstant.LAPTOP_COLLECTION)
    @Operation(summary = "Get all the laptops from the database",
            description = "getAllLaptop method will return all the laptops as list",
            method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<LaptopResponse>> getAllLaptop() {
        long laptopCount = 0;
        LOGGER.debug("Fetching all laptops from the database");
        List<Laptop> laptopList = laptopService.getAllLaptop();
        laptopCount = laptopList.stream().count();
        LOGGER.debug("Mapping laptop list to response list");
        List<LaptopResponse> responseList = laptopMapper.mapLaptopListToLaptopResponseList(laptopList);
        LOGGER.debug("Returning laptop response list");
        LOGGER.info("Laptop count is {}", laptopCount);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping(URLConstant.LAPTOP_BY_LAPTOP_ID)
    @Operation(summary = "Fetch Laptop By LaptopId", description = "getAllLaptop method will return all the laptops as list", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<LaptopResponse> getLaptopById(@PathVariable("laptop_id") int laptop_id) {
        LOGGER.debug("Fetching laptop by ID: {}", laptop_id);
        Optional<Laptop> laptop = laptopService.getLaptopById(laptop_id);
        LOGGER.debug("Mapping laptop to response by ID: {}", laptop_id);
        LaptopResponse response = laptopMapper.mapLaptopToLaptopResponseByLaptopID(laptop_id);
        LOGGER.debug("Returning response for laptop by ID: {}", laptop_id);
        LOGGER.debug("Returning response for laptop by ID: {}", laptop);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
