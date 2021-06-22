package pl.springboot2.karoljanik.notepadapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.springboot2.karoljanik.notepadapi.model.Note;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE note SET text =:text WHERE id =:id", nativeQuery = true)
//    void editNoteById(@Param("text") String text, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE note SET text =? WHERE id =?", nativeQuery = true)
    void editNoteById(String text, Long id);
}
