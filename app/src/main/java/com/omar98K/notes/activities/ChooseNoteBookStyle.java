package com.omar98K.notes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.omar98K.notes.R;
import com.omar98K.notes.adapters.AllCategoryAdapter;
import com.omar98K.notes.classes.NoteBookyStyle;

import java.util.ArrayList;

public class ChooseNoteBookStyle extends AppCompatActivity {
    private RecyclerView categoryRecyclerView;
    AllCategoryAdapter categoryAdapter;
    private RecyclerView.LayoutManager categoryLayoutManager;
    ArrayList<NoteBookyStyle> styles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_note_book_style);

        styles= new ArrayList<>();
        styles.add(new NoteBookyStyle(1,R.drawable.book1));
        styles.add(new NoteBookyStyle(2,R.drawable.book2));
        styles.add(new NoteBookyStyle(3,R.drawable.book3));
        styles.add(new NoteBookyStyle(4,R.drawable.book4));
        styles.add(new NoteBookyStyle(5,R.drawable.book5));
        styles.add(new NoteBookyStyle(6,R.drawable.book6));
        styles.add(new NoteBookyStyle(7,R.drawable.book7));
        styles.add(new NoteBookyStyle(8,R.drawable.book8));
        styles.add(new NoteBookyStyle(9,R.drawable.book9));
        styles.add(new NoteBookyStyle(10,R.drawable.book10));
        styles.add(new NoteBookyStyle(11,R.drawable.book11));
        styles.add(new NoteBookyStyle(12,R.drawable.book12));
        styles.add(new NoteBookyStyle(13,R.drawable.book13));
        styles.add(new NoteBookyStyle(14,R.drawable.book14));
        styles.add(new NoteBookyStyle(15,R.drawable.book15));
        styles.add(new NoteBookyStyle(16,R.drawable.book16));
        styles.add(new NoteBookyStyle(17,R.drawable.book17));
        styles.add(new NoteBookyStyle(18,R.drawable.book18));
        styles.add(new NoteBookyStyle(19,R.drawable.book19));
        styles.add(new NoteBookyStyle(20,R.drawable.book20));
        styles.add(new NoteBookyStyle(21,R.drawable.book21));
        styles.add(new NoteBookyStyle(22,R.drawable.book22));
        styles.add(new NoteBookyStyle(23,R.drawable.book23));
        styles.add(new NoteBookyStyle(24,R.drawable.book24));
        styles.add(new NoteBookyStyle(25,R.drawable.book25));
        styles.add(new NoteBookyStyle(26,R.drawable.book26));
        styles.add(new NoteBookyStyle(27,R.drawable.book27));
        styles.add(new NoteBookyStyle(28,R.drawable.book28));
        styles.add(new NoteBookyStyle(29,R.drawable.book29));
        //recycler of categories
        categoryRecyclerView =  findViewById(R.id.recycler_view_all_categories);
        categoryLayoutManager = new GridLayoutManager(this,3, RecyclerView.VERTICAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryAdapter = new AllCategoryAdapter(styles);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(new AllCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(ChooseNoteBookStyle.this,Create_Notebook_Page.class);
                intent.putExtra("categoryStyleId" ,styles.get(position).getImageIcon());
                startActivity(intent);
                finish();

            }
        });


    }

    public void OnClickBackFromAllStylesToAddNewNotebook(View view) {
       finish();
    }
}