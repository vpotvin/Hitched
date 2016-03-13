package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PC User on 3/13/2016.
 */
public class MusicFragment extends Fragment {
    private static final String ARTIST_NAME = "artistName";
    private static final String SONG_NAME = "songName";
    private TextView musicTextView,artistTextView;
    private ImageView musicImageView;
    private Button viewVideoButton,saveFavoriteMusicList;
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
        artistTextView = (TextView)view.findViewById(R.id.artist_text_view);
        musicImageView = (ImageView)view.findViewById(R.id.music_image_view);
        viewVideoButton = (Button)view.findViewById(R.id.music_view_video);
        saveFavoriteMusicList = (Button)view.findViewById(R.id.save_to_music_list_button);

        return view;
    }
    private class FetchItemsTask extends AsyncTask<Void,Void,List<Song>> {

        @Override
        protected List<Song> doInBackground(Void... voids) {
            return new PlaceFetchr().fetchSearchSong(songName,artistName);

        }

        @Override
        protected void onPostExecute(final List<Song> list) {
            mSongList = list;
            musicTextView.append("Name: "+list.get(0).getName());
            artistTextView.append("Artist: "+list.get(0).getSinger());
            Picasso.with(getActivity())
                    .load(list.get(0).getImgURL())
                    .into(musicImageView);
            viewVideoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(0).getStreamingURL()));
                    startActivity(browserIntent);
                }
            });
            saveFavoriteMusicList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ParseObject music = new ParseObject("FavoriteMusicList");
                    music.put("Name",list.get(0).getName());
                    music.put("Artist",list.get(0).getSinger());
                    music.put("ImageURL",list.get(0).getImgURL());
                    music.put("StreamingURL",list.get(0).getStreamingURL());
                }
            });

        }
    }
}
