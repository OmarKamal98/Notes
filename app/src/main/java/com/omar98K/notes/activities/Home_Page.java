package com.omar98K.notes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omar98K.notes.R;
import com.omar98K.notes.adapters.AllNotebooksAdapter;
import com.omar98K.notes.adapters.NotesAdapter;
import com.omar98K.notes.adapters.horizontalAdapter;
import com.omar98K.notes.classes.Note;
import com.omar98K.notes.classes.NoteBook;

import java.util.ArrayList;

public class Home_Page extends AppCompatActivity {
    public static String nameOfNoteBook="non";
    public static String currentNotebookId="non";
    private static DatabaseReference mDatabase;
    public static ValueEventListener valueEventListener;
    //book tools
    private RecyclerView bookRecyclerView;
    static horizontalAdapter bookAdapter;
    private RecyclerView.LayoutManager bookLayoutManager;
    static ArrayList<NoteBook> books=new ArrayList<>();
    //note tools
    private RecyclerView noteRecyclerView;
    static NotesAdapter noteAdapter;
    private RecyclerView.LayoutManager noteLayoutManager;
    static ArrayList<Note> notes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        //Initialize Realtime Reference.
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //sharedPreferences
        SharedPreferences preferences=getSharedPreferences("Prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("is_logged_in",true);
        editor.apply();
        //recycler of notebooks
        bookRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_notebooks);
        bookRecyclerView.setHasFixedSize(true);
        bookLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        bookRecyclerView.setLayoutManager(bookLayoutManager);
        bookAdapter = new horizontalAdapter(books);
        bookRecyclerView.setAdapter(bookAdapter);
        //notebooks data
        initNotebookData();
        //recycler of notes
        noteRecyclerView = (RecyclerView) findViewById(R.id.note_recycler_view);
        noteRecyclerView.setHasFixedSize(true);
        noteLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        noteRecyclerView.setLayoutManager(noteLayoutManager);
        noteAdapter = new NotesAdapter(notes);
        noteRecyclerView.setAdapter(noteAdapter);
        //notes data
          initNoteData();
        //onBookCLick
        bookAdapter.setOnItemClickListener(new AllNotebooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                nameOfNoteBook=books.get(position).name;
                String id=books.get(position).id;

                currentNotebookId=id;
                initNoteData();
                getIntent().putExtra("category id",id);
            }
        });
    }
    //get notebooks from the fireBase database
    public static void initNotebookData() {
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("NoteBook")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        books.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            NoteBook notebook = snapshot.getValue(NoteBook.class);
                            books.add(notebook);
                        }
                        bookAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

        //get notes from the fireBase database
        public static void initNoteData() {
            FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("Note")
                    .addValueEventListener(valueEventListener=new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           notes.clear();
                            for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                                Note note = snapshot.getValue(Note.class);
                                if (note.noteBookId.equals(currentNotebookId)) {
                                    notes.add(note);
                                }
                                else if (currentNotebookId.equals("non")) {
                                    notes.add(note);
                                }
                            }
                            noteAdapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
        }
    //add notebook in firebase database
    public static void writeNotebook(NoteBook noteBook) {
        Log.d("FIREBASE", "Writing notebook");
        String userId =FirebaseAuth.getInstance().getUid();

        mDatabase.child("User").child(userId).child("NoteBook").child(noteBook.id).setValue(noteBook);
    }
    //add note in firebase database
    public static void writeNote(Note note) {
        Log.d("FIREBASE", "Writing notebook");
        String userId =FirebaseAuth.getInstance().getUid();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase.child("User").child(userId).child("Note").child(note.idOfNote).setValue(note);
        notes.add(note);
      
    }



    public void onClickLogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent out=new Intent(this,Sign_in.class);
        startActivity(out);

    }

    public void OnClickCreateNewNoteBook(View view) {
        Intent out=new Intent(this,Create_Notebook_Page.class);
        startActivity(out);
    }

    public void OnClickShowAllNotes(View view) {
        Intent show=new Intent(this,ShowNote.class);
        startActivity(show);
    }

    public void OnClickShowAllNotebooks(View view) {
        Intent show=new Intent(this,NoteBooksShowAll.class);
        startActivity(show);
    }
}