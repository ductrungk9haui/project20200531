package com.example.appmedia;

import android.widget.ImageView;
import android.widget.TextView;

public class MusicInfor {
    private int artwork;
    private String nameSong;
    private String nameSinger;

    public MusicInfor(int artwork, String nameSong, String nameSinger) {
        this.artwork = artwork;
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
    }

    public int getArtwork() {
        return artwork;
    }

    public void setArtwork(int artwork) {
        this.artwork = artwork;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }
}
