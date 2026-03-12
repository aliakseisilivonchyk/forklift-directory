package com.github.aliakseisilivonchyk.forkliftdirectory.dto;

import java.time.Duration;

public record MalfunctionDto(int id, String startTimestamp, String endTimestamp, Duration downtime, String description) {
}