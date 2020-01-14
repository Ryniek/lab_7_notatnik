package pl.rynski.lab_7_notatnik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.rynski.lab_7_notatnik.model.Note;
import pl.rynski.lab_7_notatnik.service.NoteService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        model.addAttribute("note", new Note());
        return "index";
    }

    @PostMapping("/saveNote")
    public String saveNote(@ModelAttribute @Valid Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        noteService.saveNote(note);
        return "redirect:/";
    }

    @GetMapping("/updateNote/{id}")
    public String updateNote(@PathVariable Long id, Model model) {
        model.addAttribute("noteToUpdate", noteService.getNoteById(id));
        return "update";
    }

    @PostMapping("/updateNote/{id}")
    public String updatePost(@RequestParam String title,
                             @RequestParam String content,
                             @PathVariable Long id) {
        Note noteToUpdate = noteService.getNoteById(id);
        noteToUpdate.setTitle(title);
        noteToUpdate.setContent(content);
        noteService.updateNote(noteToUpdate);
        return "redirect:/";
    }

    @GetMapping("/deleteNote/{id}")
    public String updatePost(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/";
    }
}
