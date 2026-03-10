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

    @GetMapping("/{id}/malfunctions")
    public List<MalfunctionDto> findForkliftMalfunctions(@PathVariable int id) {
        return forkliftDirectoryService.findForkliftMalfunctions(id);
    }

    @DeleteMapping("/{id}")
    public void deleteForklift(@PathVariable int id) {
        forkliftDirectoryService.deleteById(id);
    }

    @PostMapping("")
    public ForkliftDto createForklift(@RequestBody ForkliftDto forkliftDto) {
        return forkliftDirectoryService.createForklift(forkliftDto);
    }
}
