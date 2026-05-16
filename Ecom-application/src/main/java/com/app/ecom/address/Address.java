package com.app.ecom.address;

import com.app.ecom.address.dto.AddressDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

//    public Address(String street, String city, String state, String country, String zipcode) {
//        this.street = street;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.zipcode = zipcode;
//    }

    public AddressDto toAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet(this.street);
        addressDto.setCity(this.city);
        addressDto.setState(this.state);
        addressDto.setCountry(this.country);
        addressDto.setZipcode(this.zipcode);
        return addressDto;
    }
}
