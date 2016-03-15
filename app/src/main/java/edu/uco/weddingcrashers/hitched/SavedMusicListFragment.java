package edu.uco.weddingcrashers.hitched;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC User on 3/13/2016.
 */
public class SavedMusicListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SaveMusicListAdapter mAdapter;
    private List<Song> mSongs;
    private TextView mSongNameTextView, mArtistNameTextView;
    private ActionMode mActionMode;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_music_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.saved_music_recycle_view);
        registerForContextMenu(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSongs = new ArrayList<>();
        updateUI();

        return view;
    }

    public void deleteSong(final Song song){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("FavoriteMusicList");
        query.whereEqualTo("Name", song.getName());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> songList, ParseException e) {
                if (e == null) {
                    for(int i = 0;i<songList.size();i++)
                        songList.get(i).deleteInBackground();
                    Log.d("Song", "Deleted " + songList.size() + " songs");
                } else {
                    Log.d("Song", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void updateUI() {
        ParseQuery query = new ParseQuery("FavoriteMusicList");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> items, ParseException e) {
                if (e == null) {
                    Log.d("FavoriteMusicList", "Retrieved " + items.size() + " vendors");
                    mSongs.clear();
                    for (int i = 0; i < items.size(); i++) {
                        Song song = new Song();
                        song.setName(items.get(i).getString("Name"));
                        song.setSinger(items.get(i).getString("Artist"));
                        song.setImgURL(items.get(i).getString("ImageURL"));
                        song.setStreamingURL(items.get(i).getString("StreamingURL"));
                        mSongs.add(song);
                    }
                    if(mAdapter == null){
                        mAdapter = new SaveMusicListAdapter(mSongs);
                        mRecyclerView.setAdapter(mAdapter);
                    }else{
                        mAdapter.notifyDataSetChanged();
                    }

                } else {
                    Log.d("FavoriteVendor", "Error: " + e.getMessage());
                }
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        //updateUI();
    }


    private class SavedMusicListHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private Song mSong;

        public SavedMusicListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            mSongNameTextView = (TextView) itemView.findViewById(R.id.track_name_text_view);
            mArtistNameTextView = (TextView) itemView.findViewById(R.id.artist_name_text_view);

        }

        public void bindSavedMusic(Song song) {
            mSong = song;
            mSongNameTextView.setText(mSong.getName());
            mArtistNameTextView.setText(mSong.getSinger());
        }

        @Override
        public void onClick(View view) {
            ;
            Toast.makeText(getActivity(), "Name: " + mSong.getName() + " - Singer: " + mSong.getSinger(), Toast.LENGTH_SHORT).show();
        }
        @Override
        public boolean onLongClick(View view) {
            if(mActionMode != null){
                return false;
            }
            mActionMode = getActivity().startActionMode(mCallback);
            view.setSelected(true);
            return false;
        }
        private ActionMode.Callback mCallback = new ActionMode.Callback(){
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.save_music_list_context_menu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_delete:
                        deleteSong(mSong);
                        Toast.makeText(getActivity(),"Deleted " + mSong.getName() + "from the list",Toast.LENGTH_SHORT).show();
                        updateUI();
                        mode.finish();
                        return false;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mode = null;
            }
        };


    }

    private class SaveMusicListAdapter extends RecyclerView.Adapter<SavedMusicListHolder> {

        public SaveMusicListAdapter(List<Song> songs) {
            mSongs = songs;
        }

        @Override
        public SavedMusicListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_save_music, parent, false);
            return new SavedMusicListHolder(view);
        }

        @Override
        public void onBindViewHolder(SavedMusicListHolder holder, int position) {
            Song song = mSongs.get(position);
            holder.bindSavedMusic(song);

        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }
    }



}
