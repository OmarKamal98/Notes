package com.omar98K.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omar98K.notes.R;
import com.omar98K.notes.adapters.AllNotebooksAdapter;
import com.omar98K.notes.adapters.AllNotesAdapter;
import com.omar98K.notes.classes.Note;

import java.util.ArrayList;


import static com.omar98K.notes.activities.Home_Page.nameOfNoteBook;
import static com.omar98K.notes.activities.Home_Page.noteAdapter;

public class ShowNote extends AppCompatActivity {
    private RecyclerView noteRecyclerView;
    AllNotesAdapter notesAdapter;
    private RecyclerView.LayoutManager noteLayoutManager;
    ArrayList<Note> notes;
    public static ValueEventListener valueEventListener2;
    TextView namenotebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_show_all);

        namenotebook=findViewById(R.id.nameNoteBook);

        namenotebook.setText( nameOfNoteBook );
        //notes data
        notes = Home_Page.notes;
        initNoteData2();
        //recycler of notes
        noteRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_notes);
        noteLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        noteRecyclerView.setLayoutManager(noteLayoutManager);
        notesAdapter = new AllNotesAdapter(notes);
        noteRecyclerView.setAdapter(notesAdapter);
        //onCLick

        notesAdapter.setOnItemClickListener(new AllNotebooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String name = notes.get(position).contextOfNote;
                Intent intent = new Intent(ShowNote.this,EditeNote.class);
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
        Intent as=new Intent(this,AddNewNote.class);
        startActivity(as);
        ShowNote.this.finish();
    }
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
}

