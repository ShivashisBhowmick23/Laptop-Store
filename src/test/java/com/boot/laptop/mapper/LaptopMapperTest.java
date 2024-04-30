package com.boot.laptop.mapper;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.response.LaptopResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaptopMapperTest {

    @Test
    public void testMapLaptopListToLaptopResponseList() {
        LaptopMapper laptopMapper = new LaptopMapper();

        List<Laptop> laptopList = new ArrayList<>();
        Laptop laptop1 = new Laptop();
        laptop1.setLaptop_id(1);
        laptop1.setLaptop_name("Laptop1");
        laptop1.setLaptop_price("999.99");
        Laptop laptop2 = new Laptop();
        laptop2.setLaptop_id(2);
        laptop2.setLaptop_name("Laptop2");
        laptop2.setLaptop_price("1299.99");
        laptopList.add(laptop1);
        laptopList.add(laptop2);

        List<LaptopResponse> expected = new ArrayList<>();
        LaptopResponse laptopResponse1 = new LaptopResponse();
        laptopResponse1.setLaptop_id(1);
        laptopResponse1.setLaptop_name("Laptop1");
        laptopResponse1.setLaptop_price("999.99");

        LaptopResponse laptopResponse2 = new LaptopResponse();
        laptopResponse2.setLaptop_id(2);
        laptopResponse2.setLaptop_name("Laptop2");
        laptopResponse2.setLaptop_price("1299.99");

        expected.add(laptopResponse1);
        expected.add(laptopResponse2);

        List<LaptopResponse> actual = laptopMapper.mapLaptopListToLaptopResponseList(laptopList);

        assertEquals(expected, actual);
    }
}
