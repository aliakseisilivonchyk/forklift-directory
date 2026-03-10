package com.github.aliakseisilivonchyk.forkliftdirectory.dto;

import java.time.Duration;
import java.util.Date;

public record MalfunctionDto(int id, Date startTimestamp, Date endTimestamp, Duration downtime, String description) {
}