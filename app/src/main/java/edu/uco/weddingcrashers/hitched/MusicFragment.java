package edu.uco.weddingcrashers.hitched;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC User on 3/13/2016.
 */
public class MusicFragment extends Fragment {
    private static final String ARTIST_NAME = "artistName";
    private static final String SONG_NAME = "songName";
    private TextView musicTextView;
    private String songName,artistName;
    private List<Song> mSongList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songName = getArguments().getString(SONG_NAME);
        artistName=getArguments().getString(ARTIST_NAME);
        new FetchItemsTask().execute();



    }
    public static MusicFragment newInstance(String name, String artist){
        Bundle args = new Bundle();
        args.putString(ARTIST_NAME,artist);
        args.putString(SONG_NAME,name);

        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music,container,false);
        musicTextView = (TextView)view.findViewById(R.id.music_text_view);


        return view;
    }
    private class FetchItemsTask extends AsyncTask<Void,Void,List<Song>> {

        @Override
        protected List<Song> doInBackground(Void... voids) {
            return new PlaceFetchr().fetchSearchSong(songName,artistName);

        }

        @Override
        protected void onPostExecute(List<Song> list) {
            mSongList = list;
            musicTextView.append("Track Name: "+list.get(0).getName());
            musicTextView.append("\n");
            musicTextView.append("Artist: "+list.get(0).getSinger());
            musicTextView.append("\n");
            musicTextView.append("Streaming URL: "+list.get(0).getStreamingURL());
            musicTextView.append("\n");
            musicTextView.append("Image URL: "+list.get(0).getImgURL());
            musicTextView.append("\n");
        }
    }
}
