package pl.rynski.lab_7_notatnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rynski.lab_7_notatnik.model.Note;

@Repository
public interface NoteRepostiory extends JpaRepository<Note, Long> {
}
