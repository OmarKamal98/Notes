package com.omar98K.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
 import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omar98K.notes.R;
import com.omar98K.notes.classes.Note;
import java.util.Date;

public class EditeNote  extends AppCompatActivity {
    EditText nName,nContext;
    TextView nDate;
    private static DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editenote);
        String dateText= Note.longToDate(getIntent().getLongExtra("note date",0));
        nName=findViewById(R.id.nTitle_edit_note);
        nContext=findViewById(R.id.nContext_edit_note);
        nDate=findViewById(R.id.nDate_edit_note);
        nName.setText(getIntent().getStringExtra("note name"));
        nContext.setText(getIntent().getStringExtra("note context"));
        nDate.setText(dateText);
    }
    public void OnClickBackFromEditNote(View view) {
        finish();
    }

    public void OnClickSave(View view) {

        Note note=new Note(Home_Page.currentNotebookId,Home_Page.notes.get(getIntent().getIntExtra("note position",0)).idOfNote,nName.getText().toString(),nContext.getText().toString(),new Date().getTime());
        Home_Page.writeNote(note);
        Intent intent=new Intent(EditeNote.this,NotesShowAll.class);
        startActivity(intent);
        finish();
    }

    public void Delete(View view) {
        deleteCategory();
    }
    private void deleteCategory() {
        Note note=new Note(Home_Page.currentNotebookId,Home_Page.notes.get(getIntent().getIntExtra("note position",0)).idOfNote,nName.getText().toString(),nContext.getText().toString(),new Date().getTime());
        String userId = FirebaseAuth.getInstance().getUid();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("User").child(userId ).child("Note").child(note.idOfNote);
        mDatabase.removeValue();

        Intent intent=new Intent(EditeNote.this,NotesShowAll.class);
        startActivity(intent);
        finish();

    }

}

