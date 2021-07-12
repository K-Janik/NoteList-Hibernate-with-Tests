package pl.springboot2.karoljanik.notepadapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.springboot2.karoljanik.notepadapi.model.Note;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NoteControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    Flyway flyway;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldGetAllNotes() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/notes"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        Note[] notes = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Note[].class);
        Assertions.assertEquals("text 1", notes[0].getText());

    }

    @Test
    public void shouldReturn404WhenEditingNull() throws Exception {
        Note note = new Note();
        note.setText("text");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/notes/8")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(note)))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andReturn();

        String actual = mvcResult.getResolvedException().getMessage();
        Assertions.assertEquals("No such element with id: 8",actual);

    }

    @Test
    public void shouldReturn404WhenNoNote() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/notes/{id}",10))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andReturn();

        String actual = mvcResult.getResolvedException().getMessage();
        Assertions.assertEquals("No such element with id: 10",actual);

    }

    @Test
    public void shouldPostNewElement() throws Exception {
        Note note = new Note();
        note.setId(7L);
        note.setText("text 7");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/notes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(note)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

    }

    @Test
    public void shouldDeleteGivenNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/notes/6"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

    }

    @AfterEach
    public void get() {
        flyway.clean();
        flyway.migrate();
    }
}
