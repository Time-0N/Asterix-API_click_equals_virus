package com.example.asterixapi;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AsterixService {

    private final AsterixRepository asterixRepository;

    public List<Asterix> getAllCharacters() {
        return asterixRepository.findAll();
    }

    public Asterix getCharacterById(String id) {
        return asterixRepository.findById(id).orElse(null);
    }

    public Asterix addCharacter(Asterix newCharacter) {
        return asterixRepository.save(newCharacter);
    }

    public Asterix updateCharacter(String id, Asterix updatedCharacter) {
        Asterix characterToUpdate = getCharacterById(id);

        if (characterToUpdate == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(id, updatedCharacter._id())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return asterixRepository.save(updatedCharacter);
    }

    public void deleteCharacter(String id) {
        if (!asterixRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        asterixRepository.deleteById(id);
    }
}
