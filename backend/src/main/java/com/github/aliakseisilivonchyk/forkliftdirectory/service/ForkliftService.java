package com.github.aliakseisilivonchyk.forkliftdirectory.service;

import com.github.aliakseisilivonchyk.forkliftdirectory.dto.ForkliftDto;
import com.github.aliakseisilivonchyk.forkliftdirectory.model.Forklift;
import com.github.aliakseisilivonchyk.forkliftdirectory.repository.ForkliftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForkliftService {

    private final ForkliftRepository forkliftRepository;

    public List<ForkliftDto> findAll() {
        return forkliftRepository.findAll().stream().map(ForkliftService::convertToDto).toList();
    }

    private static ForkliftDto convertToDto(Forklift forklift) {
        return new ForkliftDto(forklift.getId(), forklift.getBrand(), forklift.getBrand(),
                forklift.getCarryingCapacity(), forklift.isActive(), forklift.getUpdateTimestamp());
    }
}
