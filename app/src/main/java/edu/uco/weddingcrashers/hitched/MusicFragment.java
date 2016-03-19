package edu.uco.weddingcrashers.hitched;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button viewVideoButton,saveFavoriteMusicList,viewMusicList;
    private String songName,artistName;
    private List<Song> mSongList;
    private RecyclerView mRecyclerView;
    private MusicAdapter mAdapter;
    private ProgressDialog progDailog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songName = getArguments().getString(SONG_NAME);
        artistName=getArguments().getString(ARTIST_NAME);
        new FetchMusicTask().execute();

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
        mRecyclerView = (RecyclerView)view.findViewById(R.id.music_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progDailog = ProgressDialog.show(getActivity(), "Loading","Please wait...", true);
        progDailog.setCancelable(false);
        return view;
    }

    private class MusicHolder extends RecyclerView.ViewHolder{
        public Song mSong;
        public TextView mMusic,mArtist,mListeners;
        public ImageView mImageView;
        public Button mVideoView,mSaveFavorite,mShowFavorite;
        public MusicHolder(View itemView) {
            super(itemView);
            mMusic = (TextView)itemView.findViewById(R.id.music_title);
            mArtist = (TextView)itemView.findViewById(R.id.music_description);
            mListeners = (TextView)itemView.findViewById(R.id.music_listeners);
            mImageView = (ImageView)itemView.findViewById(R.id.music_image);
            mVideoView = (Button)itemView.findViewById(R.id.music_view_video);
            mSaveFavorite = (Button)itemView.findViewById(R.id.save_to_music_list_button);
            mShowFavorite = (Button)itemView.findViewById(R.id.view_music_list_button);


        }
        public void bindMusic(Song song){

            mSong = song;
            mMusic.setText(mSong.getName());
            mArtist.setText(mSong.getSinger());
            mListeners.setText("Views: "+ mSong.getListener());
            Picasso.with(getActivity())
                    .load(mSong.getImgURL())
                    .placeholder(R.drawable.error)
                    .error(R.drawable.error)
                    .into(mImageView);
            mVideoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mSong.getStreamingURL()));
                    startActivity(browserIntent);
                }
            });

            mSaveFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ParseObject music = new ParseObject("FavoriteMusicList");
                    music.put("Name",mSong.getName());
                    music.put("Artist",mSong.getSinger());
                    music.put("ImageURL",mSong.getImgURL());
                    music.put("StreamingURL",mSong.getStreamingURL());
                    music.saveInBackground();
                    Toast.makeText(getActivity(),"Add to list successfully",Toast.LENGTH_SHORT).show();
                }
            });

            mShowFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(),SavedMusicListActivity.class);
                    startActivity(i);
                }
            });

//            if(mSong.getCouponTitle() != "")
//                mTitle.setText(c.getBusinessName());
//            else {
//                mTitle.setText("No Title");
//            }
//            if(c.getCouponDescription() != "")
//                mDescription.setText(new StringBuilder().append(c.getCouponTitle()).append("\n").append(c.getCouponDescription()).toString());
//            if(c.getCouponEndDate() != "")
//                mExpiration.setText("Expiration: " + c.getCouponEndDate());
//            c.setLogoURL(null);
//            Picasso.with(getActivity())
//                    .load(c.getLogoURL())
//                    .placeholder(R.drawable.error)
//                    .error(R.drawable.error)
//                    .into(mImageView);
            //mImageView.setImageResource(R.drawable.a);
        }

//        @Override
//        public void onClick(View view) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mCoupon.getCouponURL()));
//            startActivity(browserIntent);
//
//        }
    }

    private class MusicAdapter extends RecyclerView.Adapter<MusicHolder>{
        private List<Song> mSongs;

        public MusicAdapter(List<Song> songs){
            mSongs = songs;
        }

        @Override
        public MusicHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.music_list_item,viewGroup,false);
            return new MusicHolder(view);
        }

        @Override
        public void onBindViewHolder(MusicHolder holder, int i) {
            Song mSong = mSongs.get(i);
            holder.bindMusic(mSong);
        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }

    }
    private class FetchMusicTask extends AsyncTask<Void,Void,List<Song>> {

        @Override
        protected List<Song> doInBackground(Void... voids) {
            return new PlaceFetchr().fetchSearchSong(songName,artistName);

        }

        @Override
        protected void onPostExecute(final List<Song> list) {
            mSongList = list;
            progDailog.dismiss();
            if(mAdapter == null) {
                mAdapter = new MusicAdapter(mSongList);
                mRecyclerView.setAdapter(mAdapter);
            }else{
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
