package com.omar98K.notes.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omar98K.notes.R;
import com.omar98K.notes.adapters.AllNotebooksAdapter;
import com.omar98K.notes.classes.Note;
import com.omar98K.notes.classes.NoteBook;

import java.util.ArrayList;
import java.util.Date;

public class NoteBooksShowAll extends AppCompatActivity {
    private RecyclerView bookRecyclerView;
    AllNotebooksAdapter bookAdapter;
    private RecyclerView.LayoutManager bookLayoutManager;
    ArrayList<NoteBook> books=Home_Page.books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_books_show_all);
        //Books data
        //recycler of notebooks
        bookRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_notebooks);
        bookLayoutManager = new GridLayoutManager(this,3,RecyclerView.VERTICAL, false);
        bookRecyclerView.setLayoutManager(bookLayoutManager);
        bookAdapter = new AllNotebooksAdapter(Home_Page.books);
        bookRecyclerView.setAdapter(bookAdapter);
        //onCLick
        bookAdapter.setOnItemClickListener(new AllNotebooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                Intent intent=new Intent(NoteBooksShowAll.this,NotesShowAll.class);
                Home_Page.currentNotebookId=books.get(position).id;
                startActivity(intent);

            }
        });
        //search filter
        EditText searchEditText=findViewById(R.id.search_edit_text);
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

    private void filter(String text) {
        ArrayList<NoteBook> filteredList = new ArrayList<>();

        for (NoteBook item : Home_Page.books) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        bookAdapter.filterList(filteredList);
    }

    public void OnClickBackFromNotebooksToHome(View view) {
        finish();
    }

    public void onFabClicked_showAllNoteBooks(View view) {
        Intent addd=new Intent(this,Create_Notebook_Page.class);
        startActivity(addd);
    }
}