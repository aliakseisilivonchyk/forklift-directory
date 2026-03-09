package com.github.aliakseisilivonchyk.forkliftdirectory.controller;

import com.github.aliakseisilivonchyk.forkliftdirectory.dto.ForkliftDto;
import com.github.aliakseisilivonchyk.forkliftdirectory.service.ForkliftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForkliftDirectoryController {

    private final ForkliftService forkliftService;

    @GetMapping("/forklifts")
    public List<ForkliftDto> findAll(@RequestParam(required = false) Optional<String> number) {
        return forkliftService.findAll(number);
    }
}
