package com.example.pet.Animals;

public class Adatos_animals {

    private String npet; //nombre mascota
    private String apet;    // edad mascota
    private String descpet; //descripcion mascota
    private String urlimage; //foto mascota
    private String petSpinner; // tipo mascota

    private String dirpers; //direccion responsable
    private String npers; //nombre responsable
    private String ubiper; // ubicacion responsable
    private String epers; // email responsable
    private String numcel; //Numero responsable
    private String num_votos;//Numero de votos

    public Adatos_animals() {
    }

    public Adatos_animals(String npet, String apet, String descpet, String urlimage, String petSpinner, String dirpers, String npers, String ubiper, String epers, String numcel, String num_votos) {
        this.npet = npet;
        this.apet = apet;
        this.descpet = descpet;
        this.urlimage = urlimage;
        this.petSpinner = petSpinner;
        this.dirpers = dirpers;
        this.npers = npers;
        this.ubiper = ubiper;
        this.epers = epers;
        this.numcel = numcel;
        this.num_votos = num_votos;
    }

    public String getNpet() {
        return npet;
    }

    public void setNpet(String npet) {
        this.npet = npet;
    }

    public String getApet() {
        return apet;
    }

    public void setApet(String apet) {
        this.apet = apet;
    }

    public String getDescpet() {
        return descpet;
    }

    public void setDescpet(String descpet) {
        this.descpet = descpet;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public String getPetSpinner() {
        return petSpinner;
    }

    public void setPetSpinner(String petSpinner) {
        this.petSpinner = petSpinner;
    }

    public String getDirpers() {
        return dirpers;
    }

    public void setDirpers(String dirpers) {
        this.dirpers = dirpers;
    }

    public String getNpers() {
        return npers;
    }

    public void setNpers(String npers) {
        this.npers = npers;
    }

    public String getUbiper() {
        return ubiper;
    }

    public void setUbiper(String ubiper) {
        this.ubiper = ubiper;
    }

    public String getEpers() {
        return epers;
    }

    public void setEpers(String epers) {
        this.epers = epers;
    }

    public String getNumcel() {
        return numcel;
    }

    public void setNumcel(String numcel) {
        this.numcel = numcel;
    }

    public String getNum_votos() {
        return num_votos;
    }

    public void setNum_votos(String num_votos) {
        this.num_votos = num_votos;
    }
}

