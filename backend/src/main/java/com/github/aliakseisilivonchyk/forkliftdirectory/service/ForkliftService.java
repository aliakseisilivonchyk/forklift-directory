package com.github.aliakseisilivonchyk.forkliftdirectory.service;

import com.github.aliakseisilivonchyk.forkliftdirectory.dto.ForkliftDto;
import com.github.aliakseisilivonchyk.forkliftdirectory.model.Forklift;
import com.github.aliakseisilivonchyk.forkliftdirectory.repository.ForkliftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForkliftService {

    private final ForkliftRepository forkliftRepository;

    public List<ForkliftDto> findAll(Optional<String> number) {
        Specification<Forklift> specification = Specification.unrestricted(); // Start with a null spec

        if (number.isPresent()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("number")), "%" + number.get().toLowerCase() + "%"));
        }

        return forkliftRepository.findAll(specification).stream().map(ForkliftService::convertToDto).toList();
    }

    private static ForkliftDto convertToDto(Forklift forklift) {
        return new ForkliftDto(forklift.getId(), forklift.getBrand(), forklift.getNumber(),
                forklift.getCarryingCapacity(), forklift.isActive(), forklift.getUpdateTimestamp());
    }
}
