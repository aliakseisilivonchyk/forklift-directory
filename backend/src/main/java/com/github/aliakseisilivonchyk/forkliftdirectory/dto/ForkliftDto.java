package com.github.aliakseisilivonchyk.forkliftdirectory.dto;

public record ForkliftDto (int id, String brand, String number, float carryingCapacity,
                           boolean isActive, String updateTimestamp) {
}
