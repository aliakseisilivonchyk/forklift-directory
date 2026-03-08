package com.github.aliakseisilivonchyk.forkliftdirectory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Forklift {

    @Id
    private int id;
    private String brand;
    private String number;
    private float carryingCapacity;
    private boolean isActive;
    private Date updateTimestamp;

    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser user;

    @OneToMany(mappedBy = "forklift")
    private List<Malfunction> malfunctions;
}
