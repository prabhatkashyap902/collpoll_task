package com.prodev.collpoll_task.model;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class model {
    String Name, Birthyear, Height, Gender, Film_count, Species_count,
           Vehicles_count, Starships_count, CreatedAt ,EditedAt;
    String url;

    public model(String Name){
this.Name=Name;
    }
    public model(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthyear() {
        return Birthyear;
    }

    public void setBirthyear(String birthyear) {
        Birthyear = birthyear;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFilm_count() {
        return Film_count;
    }

    public void setFilm_count(String film_count) {
        Film_count = film_count;
    }

    public String getSpecies_count() {
        return Species_count;
    }

    public void setSpecies_count(String species_count) {
        Species_count = species_count;
    }

    public String getVehicles_count() {
        return Vehicles_count;
    }

    public void setVehicles_count(String vehicles_count) {
        Vehicles_count = vehicles_count;
    }

    public String getStarships_count() {
        return Starships_count;
    }

    public void setStarships_count(String starships_count) {
        Starships_count = starships_count;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getEditedAt() {
        return EditedAt;
    }

    public void setEditedAt(String editedAt) {
        EditedAt = editedAt;
    }

    public String getUrl(){return url;}

    public void setUrl(String url) {
        this.url = url;
    }
}
