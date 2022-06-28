/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

public class Genre {
    private int genreID;
    private String genreName;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    
    public Genre(int genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
    
    
}
