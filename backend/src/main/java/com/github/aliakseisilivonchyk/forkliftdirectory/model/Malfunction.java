package com.github.aliakseisilivonchyk.forkliftdirectory.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;
import java.time.Duration;
import java.util.Date;

@Entity
@Data
public class Malfunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startTimestamp;
    private Date endTimestamp;

    @Formula("case when end_timestamp IS NOT NULL then age(end_timestamp, start_timestamp) else age(start_timestamp) end")
    private Duration downtime;
    private String description;

    @ManyToOne
    @JoinColumn(name = "forklift_id")
    private Forklift forklift;
}