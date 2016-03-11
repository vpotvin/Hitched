package edu.uco.weddingcrashers.hitched;

import java.util.UUID;

/**
 * Created by PC User on 3/3/2016.
 */
public class Song {
    private String name;
    private String singer;

    public UUID getSongID() {
        return songID;
    }

    private UUID songID;


    public Song() {
        songID = UUID.randomUUID();
    }

    public Song(String name, String singer) {
        songID = UUID.randomUUID();
        this.name = name;
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
