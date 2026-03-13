package com.github.aliakseisilivonchyk.forkliftdirectory.dto;

public record MalfunctionDto(int id, String startTimestamp, String endTimestamp, Long downtime, String description) {
}