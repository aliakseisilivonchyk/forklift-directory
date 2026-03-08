package com.github.aliakseisilivonchyk.forkliftdirectory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Malfunction {

    @Id
    private int id;
    private Date startTimestamp;
    private Date endTimestamp;
    private String description;

    @ManyToOne
    @JoinColumn(name = "forklift_id")
    private Forklift forklift;
}