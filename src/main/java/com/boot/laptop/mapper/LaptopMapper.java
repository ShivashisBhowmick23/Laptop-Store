package com.boot.laptop.mapper;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.repository.LaptopRepository;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.response.LaptopResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LaptopMapper {

    @Autowired
    LaptopRepository laptopRepository;

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

    public LaptopResponse mapLaptopToLaptopResponseByLaptopID(int laptop_id) {
        // Retrieve the Laptop object by its ID
        Optional<Laptop> optionalLaptop = laptopRepository.findById(laptop_id);

        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();

            // Map properties from Laptop to LaptopResponse
            LaptopResponse laptopResponse = new LaptopResponse();
            laptopResponse.setLaptop_id(laptop.getLaptop_id());
            laptopResponse.setLaptop_price(laptop.getLaptop_price());
            laptopResponse.setLaptop_name(laptop.getLaptop_name());
            // Map other properties as needed

            return laptopResponse;
        } else {
            // Handle case where Laptop object with the specified ID is not found
            return null;
        }
    }

    public List<LaptopResponse> mapLaptopToLaptopResponseByLaptopName(String laptopName) {
        List<LaptopResponse> laptopResponses = new ArrayList<>();

        // Assuming you have a method to retrieve a Laptop object by its name
        List<Laptop> laptops = laptopRepository.findByLaptopName(laptopName);

        if (laptops == null) {
            // Handle case where laptop with the given name is not found
            return null; // Or throw an exception, depending on your requirements
        }

        // Map the properties of the Laptop object to the corresponding properties of LaptopResponse
        for (Laptop laptop : laptops) {
            LaptopResponse laptopResponse = new LaptopResponse();
            laptopResponse.setLaptop_id(laptop.getLaptop_id());
            laptopResponse.setLaptop_name(laptop.getLaptop_name());
            laptopResponse.setLaptop_price(laptop.getLaptop_price());
            laptopResponses.add(laptopResponse);
        }
        return laptopResponses;
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

    public List<LaptopResponse> mapLaptopListToLaptopResponseList(List<Laptop> laptopList) {

        List<LaptopResponse> laptopResponseList = new ArrayList<>();

        for (Laptop laptop : laptopList) {
            LaptopResponse laptopResponse = new LaptopResponse();
            laptopResponse.setLaptop_id(laptop.getLaptop_id());
            laptopResponse.setLaptop_name(laptop.getLaptop_name());
            laptopResponse.setLaptop_price(laptop.getLaptop_price());
            laptopResponseList.add(laptopResponse);
        }

        return laptopResponseList;

    }
}


