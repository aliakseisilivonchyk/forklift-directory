package com.github.aliakseisilivonchyk.forkliftdirectory.service;

import com.github.aliakseisilivonchyk.forkliftdirectory.dto.ForkliftDto;
import com.github.aliakseisilivonchyk.forkliftdirectory.dto.MalfunctionDto;
import com.github.aliakseisilivonchyk.forkliftdirectory.model.Forklift;
import com.github.aliakseisilivonchyk.forkliftdirectory.model.Malfunction;
import com.github.aliakseisilivonchyk.forkliftdirectory.repository.ForkliftRepository;
import com.github.aliakseisilivonchyk.forkliftdirectory.repository.MalfunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForkliftDirectoryService {

    private final ForkliftRepository forkliftRepository;
    private final MalfunctionRepository malfunctionRepository;

    public List<ForkliftDto> findAll(Optional<String> number) {
        Specification<Forklift> specification = Specification.unrestricted(); // Start with a null spec

        if (number.isPresent()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("number")), "%" + number.get().toLowerCase() + "%"));
        }

        return forkliftRepository.findAll(specification)
                .stream()
                .map(ForkliftDirectoryService::convertToDto)
                .toList();
    }

    public List<MalfunctionDto> findForkliftMalfunctions(int forkliftId) {
        return malfunctionRepository.findAllByForkliftId(forkliftId)
                .stream()
                .map(ForkliftDirectoryService::convertToDto)
                .toList();
    }

    public void deleteById(int id) {
        forkliftRepository.deleteById(id);
    }

    private static ForkliftDto convertToDto(Forklift forklift) {
        return new ForkliftDto(forklift.getId(), forklift.getBrand(), forklift.getNumber(),
                forklift.getCarryingCapacity(), forklift.isActive(), forklift.getUpdateTimestamp());
    }

    private static MalfunctionDto convertToDto(Malfunction malfunction) {
        return new MalfunctionDto(malfunction.getId(), malfunction.getStartTimestamp(), malfunction.getEndTimestamp(),
                malfunction.getDescription());
    }
}
