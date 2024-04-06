package com.boot.laptop.controller;

import com.boot.laptop.mapper.LaptopMapper;
import com.boot.laptop.model.Laptop;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.service.LaptopService;
import com.boot.laptop.util.LaptopUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LaptopControllerTest {

    @InjectMocks
    private LaptopController laptopController;

    @Mock
    private LaptopMapper laptopMapper;

    @Mock
    private LaptopService laptopService;

    @Mock
    private LaptopUtil laptopUtil;

    @Test
    public void testAddLaptop() {
        LaptopRequest laptopRequest = new LaptopRequest();
        laptopRequest.setLaptop_id(12);
        laptopRequest.setLaptop_name("Dell");
        laptopRequest.setLaptop_price("1000.0");

        Laptop laptop = new Laptop();
        laptop.setLaptop_id(12);
        laptop.setLaptop_name("Dell");
        laptop.setLaptop_price("1000.0");

        // Mock the behavior of laptopMapper
        when(laptopMapper.mapLaptopRequestToLaptop(any(LaptopRequest.class))).thenReturn(laptop);

        // Mock the behavior of laptopUtil
        when(laptopUtil.isLaptopExists(any(Laptop.class))).thenReturn(false);

        // Mock the behavior of laptopService.addLaptop() method
        doNothing().when(laptopService).addLaptop(any(Laptop.class));

        // Perform the test
        ResponseEntity<String> response = laptopController.addLaptop(laptopRequest);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testAddLaptop_LaptopAlreadyExistException() {
        LaptopRequest laptopRequest = new LaptopRequest();
        laptopRequest.setLaptop_id(1);
        laptopRequest.setLaptop_name("Dell");
        laptopRequest.setLaptop_price("1000.0");

        Laptop laptop = new Laptop();
        laptop.setLaptop_id(1);
        laptop.setLaptop_name("Dell");
        laptop.setLaptop_price("1000.0");

        when(laptopMapper.mapLaptopRequestToLaptop(any(LaptopRequest.class))).thenReturn(laptop);
        when(laptopUtil.isLaptopExists(any(Laptop.class))).thenReturn(true);

        ResponseEntity<String> response = laptopController.addLaptop(laptopRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
