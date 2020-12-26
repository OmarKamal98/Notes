package com.omar98K.notes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.omar98K.notes.R;
import com.omar98K.notes.classes.NoteBook;

import static com.omar98K.notes.activities.Home_Page.books;

public class Create_Notebook_Page extends Activity {
    String noteBookName;
    SharedPreferences preferences;
    int categoryImage;
    ImageView img;
    EditText name1;
    static NoteBook mNoteBook=new NoteBook();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__notebook__page);
        name1=findViewById(R.id.edit_text_category_name);
        img=(ImageView)findViewById( R.id.book_category_image);
        img.setImageResource(getIntent().getIntExtra("categoryStyleId",R.drawable.book29));
    }

    public void OnClickCancle(View view) {
        finish();
    }

    public void OnClickShowStyles(View view) {
        Intent intent=new Intent(this,ChooseNoteBookStyle.class);
        startActivity(intent);
        finish();
    }

    public void onClickSave(View view) {
        noteBookName=name1.getText().toString();
        mNoteBook=new NoteBook(NoteBook.generateCategoryID(),noteBookName,getIntent().getIntExtra("categoryStyleId",R.drawable.book29));
         Home_Page.writeNotebook(mNoteBook);
        books.add(mNoteBook);
        Toast.makeText(this, "Notebook is added successfully", Toast.LENGTH_SHORT).show();
        getIntent().putExtra("CategoryName",noteBookName);
        finish();
    }
}