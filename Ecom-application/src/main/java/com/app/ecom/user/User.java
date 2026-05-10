package com.app.ecom.user;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "user")
public class User {
    public Long id;
    private String firstName;
    private String lastName;
}
