package pl.springboot2.karoljanik.notepadapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.springboot2.karoljanik.notepadapi.model.Note;
import pl.springboot2.karoljanik.notepadapi.repo.NoteDAO;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteDAO noteDAO;

    @Autowired
    public NoteService(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    public void addNote(Note note) {
        noteDAO.inertNote(note);
    }

    public List<Note> getAllNotes() {
        return noteDAO.getAllNotes();
    }

    public void editNoteById(Long id, Note note) {
//        Optional<Note> noteOptional= Optional.ofNullable(noteDAO.editNoteById(id, note));
//        return noteOptional.orElseThrow(()-> new EntityNotFoundException("No note with such id: " + id));

        noteDAO.findById(id).orElseThrow(()->new EntityNotFoundException("No such element with id: "+id));
        noteDAO.editNoteById(id, note);
    }

    public Note findById(Long id) {
        return noteDAO.findById(id).orElseThrow(()->new EntityNotFoundException("No such element with id: "+id));
    }

    public void deleteNoteById(Long id) {
        noteDAO.deleteNoteById(id);
    }
}
