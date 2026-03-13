package com.github.aliakseisilivonchyk.forkliftdirectory.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
public class Malfunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;

    @Formula("case when end_timestamp IS NOT NULL then EXTRACT(epoch FROM age(end_timestamp, start_timestamp))*1000 else EXTRACT(epoch FROM age(end_timestamp))*1000 end")
    private long downtime;
    private String description;

    @ManyToOne
    @JoinColumn(name = "forklift_id")
    private Forklift forklift;
}