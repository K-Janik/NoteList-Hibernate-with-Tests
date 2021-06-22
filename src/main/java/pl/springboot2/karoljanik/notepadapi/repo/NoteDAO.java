package pl.springboot2.karoljanik.notepadapi.repo;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.springboot2.karoljanik.notepadapi.model.Note;

import java.util.List;

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

    public void editNoteById(Long id, Note note) {
        noteRepo.editNoteById(note.getText(),id);
    }

    public void deleteNoteById(Long id) {
        noteRepo.deleteById(id);
    }

}
