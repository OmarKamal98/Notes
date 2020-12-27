package com.omar98K.notes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omar98K.notes.R;
import com.omar98K.notes.adapters.AllNotebooksAdapter;
import com.omar98K.notes.adapters.AllNotesAdapter;
import com.omar98K.notes.classes.Note;
import com.omar98K.notes.classes.NoteBook;

import java.util.ArrayList;

import static com.omar98K.notes.activities.Home_Page.books;
import static com.omar98K.notes.activities.Home_Page.initNoteData;
import static com.omar98K.notes.activities.Home_Page.nameOfNoteBook;
import static com.omar98K.notes.activities.Home_Page.noteAdapter;

public class NotesShowAll extends AppCompatActivity {
    //note tools
    private RecyclerView noteRecyclerView;
    AllNotesAdapter notesAdapter;
    private RecyclerView.LayoutManager noteLayoutManager;
    ArrayList<Note> notes;
    public static ValueEventListener valueEventListener2;
    private static DatabaseReference mDatabase;
    TextView namenotebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_show_all);

        namenotebook=findViewById(R.id.nameNoteBook);

        namenotebook.setText( nameOfNoteBook );
        //notes data
        notes = Home_Page.notes;

         initNoteData();
        //recycler of notes
        noteRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_notes);
        noteRecyclerView.setHasFixedSize(true);
        noteLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        noteRecyclerView.setLayoutManager(noteLayoutManager);
        notesAdapter = new AllNotesAdapter(notes);
        noteRecyclerView.setAdapter(notesAdapter);
        //onCLick

        notesAdapter.setOnItemClickListener(new AllNotebooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(NotesShowAll.this,EditeNote.class);
                intent.putExtra("note name", notes.get(position).titleOfNote);
                intent.putExtra("note context", notes.get(position).contextOfNote);
                intent.putExtra("note date", notes.get(position).dateOfNote);
                intent.putExtra("note position", position);
                startActivity(intent);
                finish();
            }
        });
//search filter
        EditText searchEditText = findViewById(R.id.search_edit_text_notes);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    //search filter
    private void filter(String text) {
        ArrayList<Note> filteredList = new ArrayList<>();

        for (Note item : notes) {
            if (item.titleOfNote.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        notesAdapter.filterList(filteredList);
    }

    public void OnClickBackFromNotebooksToHome(View view) {
        finish();
    }

    public void onFabClicked_showAllNotes(View view) {
        if (namenotebook.getText().toString()=="non") {
            Toast.makeText(this,"Please choose a notebook",Toast.LENGTH_LONG).show();
        }else{
        Intent as=new Intent(this,AddNewNote.class);
        startActivity(as);
        NotesShowAll.this.finish();
    }}
    //get note for notebook
    public static void initNoteData2() {
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("Note")
                .addValueEventListener(valueEventListener2=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Home_Page.notes.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            Note note = snapshot.getValue(Note.class);

                            Home_Page.notes.add(note);

                        }
                        noteAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    public void deleteNoteBook(View view) {

        AlertDialog alertDialog = new AlertDialog.Builder(NotesShowAll.this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("Delete NoteBook")
//set message
                .setMessage("Are you sure the notebook will be deleted? ")
//set positive button
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int a=0;
                        NoteBook notebook=new NoteBook(Home_Page.currentNotebookId,Home_Page.currentNotebookId,a);
                        String userId = FirebaseAuth.getInstance().getUid();
                        mDatabase= FirebaseDatabase.getInstance().getReference().child("User").child(userId ).child("NoteBook").child(notebook.getId());
                        mDatabase.removeValue();
                        Intent intent=new Intent(NotesShowAll.this,NoteBooksShowAll.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(),"The NoteBook  has been deleted",Toast.LENGTH_LONG).show();
                    }
                })
//set negative button
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

    }
}