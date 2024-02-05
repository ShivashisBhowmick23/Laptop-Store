package com.boot.laptop.mapper;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.response.LaptopResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class LaptopMapper {

    public LaptopRequest mapLaptopToLaptopRequest(Laptop laptop) {
        LaptopRequest laptopRequest = new LaptopRequest();
        laptopRequest.setLaptop_id(laptop.getLaptop_id());
        laptopRequest.setLaptop_price(laptop.getLaptop_price());
        laptopRequest.setLaptop_name(laptop.getLaptop_name());
        return laptopRequest;
    }

    public LaptopResponse mapLaptopToLaptopResponse(Laptop laptop) {
        LaptopResponse laptopResponse = new LaptopResponse();
        laptopResponse.setLaptop_id(laptop.getLaptop_id());
        laptopResponse.setLaptop_price(laptop.getLaptop_price());
        laptopResponse.setLaptop_name(laptop.getLaptop_name());

        return laptopResponse;
    }

    public Laptop mapLaptopRequestToLaptop(LaptopRequest laptopRequest) {
        Laptop laptop = new Laptop();
        laptop.setLaptop_id(laptopRequest.getLaptop_id());
        laptop.setLaptop_name(laptopRequest.getLaptop_name());
        laptop.setLaptop_price(laptopRequest.getLaptop_price());
        return laptop;
    }

    public Laptop mapLaptopResponseToLaptop(LaptopResponse laptopResponse) {
        Laptop laptop = new Laptop();
        laptop.setLaptop_id(laptopResponse.getLaptop_id());
        laptop.setLaptop_name(laptopResponse.getLaptop_name());
        laptop.setLaptop_price(laptopResponse.getLaptop_price());
        return laptop;
    }
}
