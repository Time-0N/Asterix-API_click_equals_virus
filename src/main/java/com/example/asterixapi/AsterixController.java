package com.example.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix")
@RequiredArgsConstructor
public class AsterixController {

    private final AsterixService asterixService;

    @GetMapping("/characters")
    public List<Asterix> getAllCharacters() {
        return asterixService.getAllCharacters();
    }

    @GetMapping("/characters/{id}")
    public Asterix findById(@PathVariable String id) {
        return asterixService.getCharacterById(id);
    }

    @PutMapping("/characters/{id}")
    public Asterix updateCharacter(@PathVariable String id, @RequestBody Asterix updatedCharacter) {
        return asterixService.updateCharacter(id, updatedCharacter);
    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable String id) {
        deleteCharacter(id);
    }

}
