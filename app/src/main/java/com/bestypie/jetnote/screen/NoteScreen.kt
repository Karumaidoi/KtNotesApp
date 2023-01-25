package com.bestypie.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bestypie.jetnote.R
import com.bestypie.jetnote.components.NoteButton
import com.bestypie.jetnote.components.NoteInputText
import com.bestypie.jetnote.data.NotesDataSource
import com.bestypie.jetnote.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(notes: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    //Make context
    val context = LocalContext.current;


    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name), style = TextStyle(fontWeight = FontWeight.Bold))
        }, actions = {
            Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notification Icons")
        }, backgroundColor = Color(0xFFDADFE3));

        // Content
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),text = title, label = "Title", onTextChange = {
                if (it.all{char -> char.isLetter() || char.isWhitespace()}) title = it;
            })

            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),text = description, label = "Add a note", onTextChange = {
                if (it.all{char -> char.isLetter() || char.isWhitespace()}) description = it;
            })
            
            NoteButton(text = "Save", onClick = {
                if(title.isNotEmpty() && description.isNotEmpty()) {
                    // Save
                    onAddNote(Note(title = title, description = description))
                    title = ""
                    description = ""
                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show();
                }
            })
        }
        
        Divider()
        LazyColumn{
            items(notes) {note -> RowNote(note = note, onNoteClicked = {
                onRemoveNote(note);
            })}
        }
    }
}

@Composable
fun RowNote(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier
            .clickable { }
            .padding(horizontal = 14.dp, vertical = 6.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEEE, d MMM")), style = MaterialTheme.typography.caption)
        }
    }
}


@Preview
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {});
}