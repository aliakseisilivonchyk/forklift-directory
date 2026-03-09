package com.github.aliakseisilivonchyk.forkliftdirectory.dto;

import java.util.Date;

public record MalfunctionDto(int id, Date startTimestamp, Date endTimestamp, String description) {
}