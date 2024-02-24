package com.boot.laptop.controller;

import com.boot.laptop.mapper.LaptopMapper;
import com.boot.laptop.model.Laptop;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.response.LaptopResponse;
import com.boot.laptop.service.LaptopService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LaptopControllerTest {
    @Test
    @DisplayName("Testing addLaptop method")
    void testAddLaptop() {
        // Arrange
        LaptopRequest laptopRequest = new LaptopRequest();
        laptopRequest.setLaptop_id(1);
        laptopRequest.setLaptop_name("Dell");
        laptopRequest.setLaptop_price("32000");
        // ... initialize other fields

        Laptop laptop = new Laptop();
        // ... initialize laptop fields

        LaptopResponse expectedResponse = new LaptopResponse();
        // ... initialize expected response fields

        LaptopMapper laptopMapper = mock(LaptopMapper.class);
        when(laptopMapper.mapLaptopRequestToLaptop(laptopRequest)).thenReturn(laptop);
        when(laptopMapper.mapLaptopToLaptopResponse(laptop)).thenReturn(expectedResponse);

        LaptopService laptopService = mock(LaptopService.class);

        LaptopController laptopController = new LaptopController(laptopService, laptopMapper);

        // Act
        ResponseEntity<LaptopResponse> response = laptopController.addLaptop(laptopRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(laptopMapper, times(1)).mapLaptopRequestToLaptop(laptopRequest);
        verify(laptopService, times(1)).addLaptop(laptop);
        verify(laptopMapper, times(1)).mapLaptopToLaptopResponse(laptop);
    }
}
