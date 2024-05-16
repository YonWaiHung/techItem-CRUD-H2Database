package com.example.tech.controller;

import com.example.tech.model.Tech;
import com.example.tech.repository.TechRepository;
import com.example.tech.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class TechController {

    @Autowired
    TechRepository techRepository;

    @GetMapping("/techItems")
    public ResponseEntity<List<Tech>> searchWithTechName(@RequestParam(required = false) String techName) {
        try {
            List<Tech> techItems = new ArrayList<Tech>();

            if (techName == null)
                techRepository.findAll().forEach(techItems::add);
            else
                techRepository.findByTechNameContaining(techName).forEach(techItems::add);

            if (techItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(techItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/techItems/{id}")
    public ResponseEntity<Tech> getTechItemById(@PathVariable("id") long id) {
        Optional<Tech> techData = techRepository.findById(id);

        if (techData.isPresent()) {
            return new ResponseEntity<>(techData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/techItems")
    public ResponseEntity<Tech> createTechItem(@RequestBody Tech tech) {
        try {
            Tech _tech = techRepository
                    .save(new Tech(tech.getTechType(), tech.getTechName(), tech.getAmount()));
            return new ResponseEntity<>(_tech, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/techItems/{id}")
    public ResponseEntity<Tech> updateTechItem(@PathVariable("id") long id, @RequestBody Tech tech) {
        Optional<Tech> techData = techRepository.findById(id);

        if (techData.isPresent()) {
            Tech _tech = techData.get();
            _tech.setTechType(tech.getTechType());
            _tech.setTechName(tech.getTechName());
            _tech.setAmount(tech.getAmount());
            return new ResponseEntity<>( techRepository.save(_tech), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/techItems/{id}")
    public ResponseEntity<HttpStatus> deleteTechItem(@PathVariable("id") long id) {
        try {
            techRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/techItems")
    public ResponseEntity<HttpStatus> deleteAllTechItems() {
        try {
            techRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
