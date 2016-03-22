package edu.uco.weddingcrashers.hitched;

import java.util.UUID;

/**
 * Created by PC User on 3/3/2016.
 */
public class Song {
    private static final String API_KEY="30233dd4d3780583b8741e26f6defd82";
    private String name;
    private String singer;
    private String streamingURL;
    private String imgURL;
    private String listener;

    public String getListener() {
        return listener;
    }

    public void setListener(String listener) {
        this.listener = listener;
    }

    public String getStreamingURL() {
        return streamingURL;
    }

    public void setStreamingURL(String streamingURL) {
        this.streamingURL = streamingURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

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
