package com.bestypie.jetnote.screen

import androidx.lifecycle.ViewModel
import com.bestypie.jetnote.data.NotesDataSource
import com.bestypie.jetnote.model.Note

class NoteViewModel: ViewModel() {
    private var noteList = mutableListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note);
    }

    fun removeNote(note: Note) {
        noteList.remove(note);
    }

    fun getAllNotes(): List<Note> {
        return  noteList;
    }
}