package com.github.aliakseisilivonchyk.forkliftdirectory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AppUser {

    @Id
    private int id;
    private String firstName;
    private String secondName;
    private String lastName;
}
