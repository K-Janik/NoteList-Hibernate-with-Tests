package pl.springboot2.karoljanik.notepadapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.springboot2.karoljanik.notepadapi.model.Note;
import pl.springboot2.karoljanik.notepadapi.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public void addNote(@RequestBody Note note) {
        noteService.addNote(note);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping(path = "{id}")
    public Note getById(@PathVariable("id") Long id) {
        return noteService.findById(id);
    }

    @PutMapping(path = "{id}")
    public void editNoteById(@PathVariable("id") Long id, @RequestBody Note note) {
        noteService.editNoteById(id, note);
    }

    @DeleteMapping(path = "{id}")
    public void deleteNoteById(@PathVariable("id") Long id) {
        noteService.deleteNoteById(id);
    }
}
