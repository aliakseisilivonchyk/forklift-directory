package com.github.aliakseisilivonchyk.forkliftdirectory.dto;

import java.util.Date;

public record ForkliftDto (int id, String brand, String number, float carryingCapacity,
                           boolean isActive, Date updateTimestamp) {}
