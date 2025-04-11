package com.drzave.controller;


import com.drzave.dto.CreateDrzavaDto;
import com.drzave.dto.DrzavaDto;
import com.drzave.service.DrzavaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drzave")
@RequiredArgsConstructor
public class DrzavaController {

    private final DrzavaService drzavaService;

    @GetMapping()
    public ResponseEntity<List<DrzavaDto>> getAll() {
        List<DrzavaDto> drzave = drzavaService.findAll();
        return ResponseEntity.ok(drzave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrzavaDto> getById(@PathVariable Long id) {
        DrzavaDto dto = drzavaService.findById(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping()
    public ResponseEntity<DrzavaDto> create(@RequestBody CreateDrzavaDto dto) {
        DrzavaDto createdDrzava = drzavaService.create(dto);
        return ResponseEntity.status(201).body(createdDrzava);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrzavaDto> update(@PathVariable Long id, @RequestBody CreateDrzavaDto dto) {
        DrzavaDto updatedDrzava = drzavaService.update(id, dto);
        return ResponseEntity.ok(updatedDrzava);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        drzavaService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
