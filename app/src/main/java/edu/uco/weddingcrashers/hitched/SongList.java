package edu.uco.weddingcrashers.hitched;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by PC User on 3/3/2016.
 */
public class SongList {
    private static SongList sSongList;
    private List<Song> mSongs;

    public static SongList get(Context context){
        if(sSongList == null){
            sSongList = new SongList(context);
        }
        return sSongList;
    }

    private SongList(Context context){
        mSongs = new ArrayList<>();



    }

    public List<Song> getVendors(){
        return mSongs;
    }

    public Song getSong(UUID id){
        for (Song song:mSongs){
            if(song.getSongID().equals(id)){
                return song;
            }
        }
        return null;
    }

    public void addSong(Song song){
        mSongs.add(song);
    }


}


