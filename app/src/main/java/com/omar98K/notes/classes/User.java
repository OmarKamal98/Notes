package com.omar98K.notes.classes;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String name;
    public String id;
    public String email;
    public Map<String , NoteBook> category  = new HashMap<>();
    public Map<String , Note> notes  = new HashMap<>();


    public User() {
    }
    public User(String name1,String email1) {
        this.name=name1;

        this.email=email1;
    }

}
