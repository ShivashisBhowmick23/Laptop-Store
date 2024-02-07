package com.boot.laptop.mapper;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.request.LaptopRequest;
import com.boot.laptop.response.LaptopResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        /* Explanation of mapLaptopListToLaptopResponseList method
        List<LaptopResponse> laptopResponseList = new ArrayList<>();:

This line creates a new ArrayList named laptopResponseList that will hold instances of the LaptopResponse class.
for (Laptop laptop : laptopList) {:

This starts a loop that iterates over each Laptop object in the laptopList parameter passed to the method.
LaptopResponse laptopResponse = new LaptopResponse();:

Inside the loop, a new instance of LaptopResponse is created for each Laptop object in the laptopList.
Mapping:

Each field of the laptopResponse object is set based on the corresponding field of the laptop object. For example:
laptopResponse.setId(laptop.getId()); sets the id field of the laptopResponse object to the id field of the laptop object.
laptopResponse.setBrand(laptop.getBrand()); sets the brand field of the laptopResponse object to the brand field of the laptop object.
Similarly, other fields like model, processor, ramSize, and storageSize are mapped from the laptop object to the laptopResponse object.
laptopResponseList.add(laptopResponse);:

After all fields are mapped, the laptopResponse object is added to the laptopResponseList.
return laptopResponseList;:

After all Laptop objects in the laptopList have been processed, the method returns the laptopResponseList, which contains LaptopResponse objects mapped from the laptopList.*/
    }
}


