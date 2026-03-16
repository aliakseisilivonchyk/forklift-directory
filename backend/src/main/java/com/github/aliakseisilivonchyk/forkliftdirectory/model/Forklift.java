package com.github.aliakseisilivonchyk.forkliftdirectory.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Entity
@Data
public class Forklift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String number;
    private float carryingCapacity;
    @Formula("case when (SELECT count(*) FROM malfunction WHERE malfunction.forklift_id = id AND malfunction.end_timestamp IS NULL) > 0 then false else true end")
    private boolean isActive;
    private LocalDateTime updateTimestamp;

    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser user;
}
