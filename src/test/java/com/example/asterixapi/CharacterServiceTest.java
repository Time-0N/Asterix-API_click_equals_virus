package com.example.asterixapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class CharacterServiceTest {

    @Test
    void findAllCharacters() {
        AsterixRepository repo = Mockito.mock(AsterixRepository.class);
        Mockito.when(repo.findAll()).thenReturn(
                List.of(
                        new Asterix("1", "Asterix", 35, "Krieger"),
                        new Asterix("2", "Obelix", 45, "Krieger")
                )
        );

        AsterixService service = new AsterixService(repo);

        List<Asterix> actual = service.getAllCharacters();

        List<Asterix> expected = List.of(
                new Asterix("1", "Asterix", 35, "Krieger"),
                new Asterix("2", "Obelix", 45, "Krieger")
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findCharacterById() {
        AsterixRepository repo = Mockito.mock(AsterixRepository.class);
        Mockito.when(repo.findById("1")).thenReturn(
                Optional.of(new Asterix("1", "Asterix", 35, "Krieger"))
        );

        AsterixService service = new AsterixService(repo);

        Asterix actual = service.getCharacterById("1");

        Asterix expected = new Asterix("1", "Asterix", 35, "Krieger");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateCharacter() {
        AsterixRepository repo = Mockito.mock(AsterixRepository.class);

        Asterix expected = new Asterix("2", "Idefix", 7, "Hund");
        Mockito.when(repo.save(new Asterix("1", "Asterix", 35, "Krieger"))).thenReturn(Optional.of(expected));

        AsterixService service = new AsterixService(repo);

        Asterix actual = service.updateCharacter("2", new Asterix("2", "Idefix", 7, "Hund"));


        Assertions.assertEquals(expected, actual);
    }
}
