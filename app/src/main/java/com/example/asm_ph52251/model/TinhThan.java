package com.example.asm_ph51505.model;

import java.io.Serializable;

public class TinhThan implements Serializable {
    private int id;
    private String text;
    private String date;
    private int iduser;

    public TinhThan() {
    }

    public TinhThan(int id, String text, String date, int iduser) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.iduser = iduser;
    }

    public TinhThan(String date, String text) {
        this.date = date;
        this.text = text;
    }

    public TinhThan(String date, String text, int iduser) {
        this.date = date;
        this.text = text;
        this.iduser = iduser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
