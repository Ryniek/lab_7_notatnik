package pl.rynski.lab_7_notatnik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.rynski.lab_7_notatnik.model.Note;
import pl.rynski.lab_7_notatnik.repository.NoteRepostiory;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private NoteRepostiory noteRepostiory;

    @Autowired
    public NoteService(NoteRepostiory noteRepostiory) {
        this.noteRepostiory = noteRepostiory;
        noteRepostiory.save(new Note("First note", "Content"));
        noteRepostiory.save(new Note("Second note", "Lalalalalaalal"));
        noteRepostiory.save(new Note("Third note", "Michumichumichu"));
        noteRepostiory.save(new Note("4th note", "Sialalalala"));
    }

    public List<Note> getAllNotes() {
        return noteRepostiory.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Note getNoteById(Long id) {
        return noteRepostiory.findById(id).get();
    }

    public void deleteNote(Long id) {
        noteRepostiory.deleteById(id);
    }

    public void saveNote(Note note) {
        noteRepostiory.save(note);
    }

    public void updateNote(Note note) {
        Optional<Note> noteToUpdate = noteRepostiory.findById(note.getId());
        if(!note.getTitle().isEmpty())
            noteToUpdate.get().setTitle(note.getTitle());
        if(!note.getContent().isEmpty())
            noteToUpdate.get().setContent(note.getContent());
        noteRepostiory.save(noteToUpdate.get());
    }
}
