package com.example.bus_e_noadmin.model;

public class Model {
    private String textView1;
    private String textView2;
    private int imageView1;
    private int imageView2;

    public Model(String textView1, String textView2, int imageView1,int imageView2){
        this.textView1 = textView1;
        this.textView2 = textView2;
        this.imageView1 = imageView1;
        this.imageView2 = imageView2;
    }

    public String getTextView1() {
        return textView1;
    }

    public String getTextView2() {
        return textView2;
    }

    public int getImageView1() {
        return imageView1;
    }

    public int getImageView2() {
        return imageView2;
    }
}
