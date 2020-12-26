package com.omar98K.notes.classes;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NoteBook {
    public String id;
    public String name;
    public int imageIcon;
    public ArrayList<Note> mNotes=new ArrayList<>();
    public NoteBook() {
    }

    public NoteBook(String id, String name, int imageIcon) {
        this.id = id;
        this.name = name;
        this.imageIcon = imageIcon;
    }

     public static String generateCategoryID(){
        return FirebaseDatabase.getInstance().getReference().child("user").child("category").push().getKey();

    }
//// id getter and setter
//    public void setId(int id) {
//        this.id = id;
//    }
//    public int getId() {
//        return id;
//    }
////  name getter and setter
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
////   image getter and setter
//    public int getImageIcon() {
//        return imageIcon;
//    }
//    public void setImageIcon(int imageIcon) {
//        this.imageIcon = imageIcon;
//    }

}
