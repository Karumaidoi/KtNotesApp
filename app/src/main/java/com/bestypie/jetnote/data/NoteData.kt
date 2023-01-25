package com.bestypie.jetnote.data

import com.bestypie.jetnote.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return  listOf(
            Note(title = "The camp and the city", description = "However we made it using Flutter, it was just an amazing job"),
            Note(title = "Empire Adventures", description = "Sometimes you wanna code, sometime you wanna debug, just sometimes")
        )
    }
}