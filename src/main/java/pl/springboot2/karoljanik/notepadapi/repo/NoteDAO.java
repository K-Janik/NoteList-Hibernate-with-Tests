package pl.springboot2.karoljanik.notepadapi.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.springboot2.karoljanik.notepadapi.model.Note;

import java.util.List;
import java.util.Optional;

@Component
public class NoteDAO {

    NoteRepo noteRepo;

    @Autowired
    public NoteDAO(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public void inertNote(Note note) {
        noteRepo.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }

    public Optional<Note> findById(Long id) {
        return noteRepo.findById(id);
    }

    public void editNoteById(Long id, Note note) {
       noteRepo.editNoteById(note.getText(),id);
    }

    public void deleteNoteById(Long id) {
        noteRepo.deleteById(id);
    }

}
