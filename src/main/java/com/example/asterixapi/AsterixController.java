package com.example.asterixapi;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asterix")
public class AsterixController {

    private final AsterixRepository asterixRepository;

    public AsterixController(AsterixRepository asterixRepository) {
        this.asterixRepository = asterixRepository;
    }

    @GetMapping("/characters")
    public List<Asterix> getAllCharacters() {

        return asterixRepository.findAll();
    }

    @PostMapping("/characters")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Asterix> addList(@RequestBody Asterix asterix) {
        asterixRepository.insert(asterix)
        return null;
    }

    @PutMapping("/characters/{id}")
    public List<Asterix> updateCharacter(@RequestBody Asterix asterix, @PathVariable String id) {
        Optional<Asterix> optional = asterixRepository.findById(id);

        if (optional.isPresent()) {
            Asterix a = optional.get();
            a.setName(asterix.getName());
            // ...
            asterixRepository.save(a);
        }
    }
}
