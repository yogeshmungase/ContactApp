package com.app.contactapp.Model;

public class Contact {
    private int imageResId;
    private String name;
    private String number;

    public Contact(int imageResId, String name, String number) {
        this.imageResId = imageResId;
        this.name = name;
        this.number = number;
    }

    public Contact( String name, String number) {
        this.name = name;
        this.number = number;
    }



    public int getImageResId() { return imageResId; }
    public String getName() { return name; }
    public String getNumber() { return number; }
}
