package com.github.aliakseisilivonchyk.forkliftdirectory.controller;

import com.github.aliakseisilivonchyk.forkliftdirectory.dto.ForkliftDto;
import com.github.aliakseisilivonchyk.forkliftdirectory.dto.MalfunctionDto;
import com.github.aliakseisilivonchyk.forkliftdirectory.service.ForkliftDirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forklifts")
@RequiredArgsConstructor
public class ForkliftDirectoryController {

    private final ForkliftDirectoryService forkliftDirectoryService;

    @GetMapping("")
    public List<ForkliftDto> findAll(@RequestParam(required = false) Optional<String> number) {
        return forkliftDirectoryService.findAll(number);
    }

    @GetMapping("/{forkliftId}/malfunctions")
    public List<MalfunctionDto> findForkliftMalfunctions(@PathVariable int forkliftId) {
        return forkliftDirectoryService.findForkliftMalfunctions(forkliftId);
    }

    @PostMapping("")
    public ForkliftDto createForklift(@RequestBody ForkliftDto forkliftDto) {
        return forkliftDirectoryService.createForklift(forkliftDto);
    }

    @DeleteMapping("/{forkliftId}")
    public void deleteForklift(@PathVariable int forkliftId) {
        forkliftDirectoryService.deleteForkliftById(forkliftId);
    }

    @PostMapping("/{forkliftId}/malfunctions")
    public MalfunctionDto createMalfunction(@PathVariable int forkliftId, @RequestBody MalfunctionDto malfunctionDto) {
        return forkliftDirectoryService.createMalfunction(forkliftId, malfunctionDto);
    }

    @DeleteMapping("/{forkliftId}/malfunctions/{malfunctionId}")
    public void deleteMalfunction(@PathVariable int forkliftId, @PathVariable int malfunctionId) {
        forkliftDirectoryService.deleteMalfunctionById(malfunctionId);
    }
}
