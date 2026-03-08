package com.github.aliakseisilivonchyk.forkliftdirectory.controller;

import com.github.aliakseisilivonchyk.forkliftdirectory.dto.ForkliftDto;
import com.github.aliakseisilivonchyk.forkliftdirectory.service.ForkliftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForkliftDirectoryController {

    private final ForkliftService forkliftService;

    @GetMapping("/forklifts")
    public List<ForkliftDto> findAll() {
        return forkliftService.findAll();
    }
}
