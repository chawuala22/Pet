package com.example.pet;

public class Adatos_dog {

    private String npet;
    private String apet;
    private String descpet;
    private String urlimage;

    public Adatos_dog(String npet, String apet, String descpet, String urlimage) {
        this.npet = npet;
        this.apet = apet;
        this.descpet = descpet;
        this.urlimage = urlimage;
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

    public Adatos_dog() {
    }
}

