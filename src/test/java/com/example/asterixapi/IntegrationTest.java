package com.example.asterixapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private AsterixRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    void shouldReturnAsterixAndObelix_whenTheyAreStoredInDb() throws Exception {

        // given
        repo.save(new Asterix("1", "Asterix", 35, "Krieger"));
        repo.save(new Asterix("1", "Obelix", 45, "Krieger"));

        // when
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/characters")
        )

        // then
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andExpect(
                        MockMvcResultMatchers.content().json("""
                        [
                            {
                                "_id": "1",
                                "name": "Asterix",
                                "age": 35,
                                "status": "Krieger"
                            },
                            {
                                "_id": "2",
                                "name": "Obelix",
                                "age": 45,
                                "status": "Krieger"
                            }
                        ]
""")
                );

    }

    @Test
    @DirtiesContext
    void whenUsingPut_thenAddElement() throws Exception {

        // given

        // when
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/characters/3")
                        .content("""
                                {
                                    "_id": "3",
                                    "name": "Majestix",
                                    "age": 65,
                                    "status": "Chef"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
        )

        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "_id": "3",
                                    "name": "Majestix",
                                    "age": 65,
                                    "status": "Chef"
                                }
"""));

        List<Asterix> actual = repo.findAll();
        List<Asterix> expected = List.of(
                new Asterix("3", "Majestix", 65, "Chef")
        );
        assertEquals(expected, actual);

    }

}
