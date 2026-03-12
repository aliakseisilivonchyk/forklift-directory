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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForkliftDirectoryService {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

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
        return malfunctionRepository.findAllByForkliftIdOrderByStartTimestampDesc(forkliftId)
                .stream()
                .map(ForkliftDirectoryService::convertToDto)
                .toList();
    }

    public ForkliftDto createForklift(ForkliftDto forkliftDto) {
        Forklift forklift = convertToModel(forkliftDto);
        forklift.setUpdateTimestamp(LocalDateTime.now());
        Forklift savedForklift = forkliftRepository.save(forklift);
        return convertToDto(savedForklift);
    }

    public void deleteForkliftById(int id) {
        forkliftRepository.deleteById(id);
    }

    public ForkliftDto updateForklift(int forkliftId, ForkliftDto forkliftDto) {
        Forklift existingForklift = forkliftRepository.findById(forkliftId).get();
        existingForklift.setUpdateTimestamp(LocalDateTime.now());
        existingForklift.setBrand(forkliftDto.brand());
        existingForklift.setNumber(forkliftDto.number());
        existingForklift.setCarryingCapacity(forkliftDto.carryingCapacity());
        Forklift savedForklift = forkliftRepository.save(existingForklift);
        return convertToDto(savedForklift);
    }

    public MalfunctionDto createMalfunction(int forkliftId, MalfunctionDto malfunctionDto) {
        Forklift existingForklift = forkliftRepository.findById(forkliftId).get();
        Malfunction malfunction = convertToModel(malfunctionDto);
        malfunction.setForklift(existingForklift);
        Malfunction savedMalfunction = malfunctionRepository.save(malfunction);
        return convertToDto(savedMalfunction);
    }

    public void deleteMalfunctionById(int id) {
        malfunctionRepository.deleteById(id);
    }

    public MalfunctionDto updateMalfunction(int malfunctionId, MalfunctionDto malfunctionDto) {
        Malfunction existingMalfunction = malfunctionRepository.findById(malfunctionId).get();
        existingMalfunction.setStartTimestamp(LocalDateTime.parse(malfunctionDto.startTimestamp(), DATE_FORMATTER));
        existingMalfunction.setEndTimestamp(LocalDateTime.parse(malfunctionDto.endTimestamp(), DATE_FORMATTER));
        existingMalfunction.setDescription(malfunctionDto.description());
        Malfunction savedMalfunction = malfunctionRepository.save(existingMalfunction);
        return convertToDto(savedMalfunction);
    }

    private static ForkliftDto convertToDto(Forklift forklift) {
        return new ForkliftDto(forklift.getId(), forklift.getBrand(), forklift.getNumber(),
                forklift.getCarryingCapacity(), forklift.isActive(), forklift.getUpdateTimestamp().format(DATE_FORMATTER));
    }

    private static Forklift convertToModel(ForkliftDto forkliftDto) {
        Forklift forklift = new Forklift();
        forklift.setBrand(forkliftDto.brand());
        forklift.setNumber(forkliftDto.number());
        forklift.setCarryingCapacity(forkliftDto.carryingCapacity());
        forklift.setActive(forkliftDto.isActive());

        return forklift;
    }

    private static MalfunctionDto convertToDto(Malfunction malfunction) {
        return new MalfunctionDto(malfunction.getId(), malfunction.getStartTimestamp().format(DATE_FORMATTER),
                malfunction.getEndTimestamp().format(DATE_FORMATTER), malfunction.getDowntime(), malfunction.getDescription());
    }

    private static Malfunction convertToModel(MalfunctionDto malfunctionDto) {
        Malfunction malfunction = new Malfunction();
        malfunction.setStartTimestamp(LocalDateTime.parse(malfunctionDto.startTimestamp(), DATE_FORMATTER));
        malfunction.setEndTimestamp(LocalDateTime.parse(malfunctionDto.endTimestamp(), DATE_FORMATTER));
        malfunction.setDescription(malfunctionDto.description());

        return malfunction;
    }
}
