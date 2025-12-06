package com.app.ecom;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "firstName", "lastName"})
public class User {

    private long id;
    private String firstName;
    private String lastName;

}
