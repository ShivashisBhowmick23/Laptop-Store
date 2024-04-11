package com.boot.laptop.controller;


import com.boot.laptop.constant.URLConstant;
import com.boot.laptop.exception.LaptopAlreadyExistException;
import com.boot.laptop.exception.LaptopNotFoundException;
import com.boot.laptop.mapper.LaptopMapper;
import com.boot.laptop.model.Laptop;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.response.LaptopResponse;
import com.boot.laptop.service.LaptopService;
import com.boot.laptop.util.LaptopUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping(URLConstant.LAPTOP_STORE)
public class LaptopController {

    private final LaptopService laptopService;
    private final LaptopMapper laptopMapper;
    private final LaptopUtil laptopUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(LaptopController.class);

    @Autowired
    public LaptopController(LaptopService laptopService, LaptopMapper laptopMapper, LaptopUtil laptopUtil) {
        this.laptopService = laptopService;
        this.laptopMapper = laptopMapper;
        this.laptopUtil = laptopUtil;
    }

    /**
     * Add Laptop into the database
     *
     * @param laptopRequest the request body containing the details of the laptop to be added
     * @return the response entity containing the inserted laptop
     */
    @PostMapping(URLConstant.ADD_LAPTOP_INTO_STORE)
    @Operation(summary = "Add Laptop into the database", description = "addLaptop method will add into the database and return inserted Laptop", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<String> addLaptop(@RequestBody LaptopRequest laptopRequest) {
        try {
            // Mapping laptop request to Laptop
            LOGGER.debug("Mapping laptop request to Laptop");
            Laptop request = laptopMapper.mapLaptopRequestToLaptop(laptopRequest);

            // Check if laptop already exists in the database
            if (laptopUtil.isLaptopExists(request)) {
                throw new LaptopAlreadyExistException("Laptop is already present in the database");
            }

            // Adding laptop to database
            LOGGER.debug("Adding laptop to database");
            laptopService.addLaptop(request);

            // Mapping laptop to laptop response
            LOGGER.debug("Mapping laptop to laptop response");
            LaptopResponse response = laptopMapper.mapLaptopToLaptopResponse(request);

            // Returning response with status OK
            LOGGER.debug("Returning response with status OK");
            return ResponseEntity.status(HttpStatus.OK).body(response + "Laptop added into the database successfully");
        } catch (LaptopAlreadyExistException laptopAlreadyExistException) {
            LOGGER.error("Error: Laptop already exists", laptopAlreadyExistException);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Laptop already exists");
        }
    }


    @GetMapping(URLConstant.LAPTOP_COLLECTION)
    @Operation(summary = "Get all the laptops from the database", description = "getAllLaptop method will return all the laptops as list", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<List<LaptopResponse>> getAllLaptop() {
        long laptopCount;
        LOGGER.debug("Fetching all laptops from the database");
        List<Laptop> laptopList = laptopService.getAllLaptop();
        laptopCount = laptopList.size();
        LOGGER.debug("Mapping laptop list to response list");
        List<LaptopResponse> responseList = laptopMapper.mapLaptopListToLaptopResponseList(laptopList);
        LOGGER.debug("Returning laptop response list");
        LOGGER.info("Laptop count is {}", laptopCount);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    /**
     * Fetch Laptop By LaptopId method will return all the laptops as list
     *
     * @param laptop_id description of parameter
     * @return description of return value
     */
    @GetMapping(URLConstant.LAPTOP_BY_LAPTOP_ID)
    @Operation(summary = "Fetch Laptop By LaptopId", description = "getAllLaptop method will return all the laptops as list", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<LaptopResponse> getLaptopById(@PathVariable("laptop_id") int laptop_id) {
        try {
            LOGGER.debug("Fetching laptop by ID: {}", laptop_id);

            // Check if the laptop ID exists
            if (!laptopUtil.isLaptopIdExists(laptop_id)) {
                throw new LaptopNotFoundException("Laptop not found with ID: " + laptop_id);
            }

            // Fetch the laptop from the service
            Optional<Laptop> laptop = laptopService.getLaptopById(laptop_id);

            // Check if the laptop is present
            if (laptop.isEmpty()) {
                throw new LaptopNotFoundException("Laptop not found with ID: " + laptop_id);
            }

            // Map the laptop to response
            LOGGER.debug("Mapping laptop to response by ID: {}", laptop_id);
            LaptopResponse response = laptopMapper.mapLaptopToLaptopResponseByLaptopID(laptop_id);

            LOGGER.debug("Returning response for laptop by ID: {}", laptop_id);
            LOGGER.debug("Returning response for laptop by ID: {}", laptop);

            // Return the response with status OK
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (LaptopNotFoundException laptopNotFoundException) {
            // If laptop not found, set error message and return with status NOT_FOUND
            LaptopResponse laptopResponse = new LaptopResponse();
            laptopResponse.setErrorMsg("No Laptop found with the laptop id: " + laptop_id);
            LOGGER.debug("Error: Laptop not found", laptopNotFoundException);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(laptopResponse);
        }
    }

    @DeleteMapping(URLConstant.LAPTOP_BY_LAPTOP_ID)
    @Operation(summary = "Fetch Laptop By LaptopId", description = "getAllLaptop method will return all the laptops as list", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<String> deleteByLaptopID(int laptop_id) {
        try {
            LOGGER.info("Attempting to delete laptop with ID: {}", laptop_id);

            // Check if the laptop exists
            List<Laptop> laptopList = laptopService.findLaptopByIdWithUsers(laptop_id);
            if (laptopList.isEmpty()) {
                LOGGER.warn("No Laptop found with requested laptop id: {}", laptop_id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Laptop found with requested laptop id: " + laptop_id);
            }

            // Check if any laptop in the list is referenced in the user_laptop table
            for (Laptop laptop : laptopList) {
                if (!laptop.getUserSet().isEmpty()) {
                    LOGGER.warn("Cannot delete laptop with id: {} as it is referenced in user_laptop table", laptop_id);
                    LOGGER.warn("Number of users using this laptop: {}", laptop.getUserSet().size());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete laptop with id: " + laptop_id + " as it is referenced in user_laptop table");
                }
            }

            // If no laptop in the list is referenced, delete the laptops
            for (Laptop laptop : laptopList) {
                laptopService.deleteLaptopByLaptopID(laptop.getLaptop_id());

                // Log the details of the deleted laptop
                LOGGER.info("Laptop deleted successfully with laptop ID: {}", laptop.getLaptop_id());
            }

            // Return a success response
            return ResponseEntity.status(HttpStatus.OK).body("Laptop(s) deleted successfully with laptop ID: " + laptop_id);
        } catch (Exception e) {
            LOGGER.error("An error occurred while deleting laptop(s) with ID: {}", laptop_id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping(URLConstant.LAPTOP_BY_LAPTOP_NAME)
    @Operation(summary = "Fetch Laptop By LaptopId", description = "getAllLaptop method will return all the laptops as list", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ResponseEntity<List<LaptopResponse>> getByLaptopName(@RequestHeader (name = "request-header" ) String customHeader,@RequestParam("laptop_name") String laptop_name,HttpServletResponse response) {
        try {
                List<Laptop> laptops = laptopService.getLaptopsByLaptopName(laptop_name.toUpperCase());

                if (laptops.isEmpty()) {
                    throw new LaptopNotFoundException("Laptop not found with name: " + laptop_name);
                }

                LOGGER.info("Mapping laptops to response by name: {}", laptop_name);
                List<LaptopResponse> responses = laptopMapper.mapLaptopListToLaptopResponseList(laptops);

                LOGGER.info("Returning response for laptops by name: {}", laptop_name);
                LOGGER.info("Returning response for laptops by name: {}", laptops);
            if (Objects.equals(customHeader, "request-header")) {
                response.addCookie(new Cookie("status_code", "200"));
            }


            return ResponseEntity.status(HttpStatus.OK).body(responses);
        } catch (LaptopNotFoundException laptopNotFoundException) {
            LaptopResponse laptopResponse = new LaptopResponse();
            laptopResponse.setErrorMsg("No Laptop found with the name: " + laptop_name);
            LOGGER.info("Not able to retrieve data");
            response.addCookie(new Cookie("status_code", "500"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(laptopResponse));
        }
    }

}
