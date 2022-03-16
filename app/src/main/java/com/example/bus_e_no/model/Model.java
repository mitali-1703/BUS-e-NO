package com.example.bus_e_no.model;

public class Model {
    private String textView1;
    private String textView2;
    private int imageView;

    public Model(String textView1, String textView2, int imageView){
        this.textView1 = textView1;
        this.textView2 = textView2;
        this.imageView = imageView;
    }

    public String getTextView1() {
        return textView1;
    }

    public String getTextView2() {
        return textView2;
    }

    public int getImageView() {
        return imageView;
    }
}
