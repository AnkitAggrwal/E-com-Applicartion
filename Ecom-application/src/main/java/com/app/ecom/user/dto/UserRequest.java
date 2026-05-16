package com.app.ecom.user.dto;

import com.app.ecom.address.dto.AddressDto;
import com.app.ecom.user.UserRole;
import lombok.Data;

@Data
public class UserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private AddressDto addressDto;
}
