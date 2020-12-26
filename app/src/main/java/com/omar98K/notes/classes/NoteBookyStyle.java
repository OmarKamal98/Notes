package com.omar98K.notes.classes;

public class NoteBookyStyle {
    private int id;
    private int imageIcon;
    public NoteBookyStyle(int id, int imageIcon){
        this.id=id;
        this.imageIcon=imageIcon;
    }
    // id getter and setter
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    //   image getter and setter
    public int getImageIcon() {
        return imageIcon;
    }
    public void setImageIcon(int imageIcon) {
        this.imageIcon = imageIcon;
    }

}
