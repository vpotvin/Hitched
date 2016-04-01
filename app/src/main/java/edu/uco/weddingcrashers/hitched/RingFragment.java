package edu.uco.weddingcrashers.hitched;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tung on 3/31/2016.
 */
public class RingFragment extends android.support.v4.app.Fragment {
    private List<Ring> ringList;
    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private RingAdapter mAdapter;
    private ProgressDialog progDailog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ring, null);
       // mTextView = (TextView) view.findViewById(R.id.ring_text_view);
        ringList = new ArrayList<>();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.ring_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progDailog = ProgressDialog.show(getActivity(), "Loading", "Please wait...", true);
        progDailog.setCancelable(false);
        new FetchRingTask().execute();
        return view;
    }

    private class FetchRingTask extends AsyncTask<Void, Void, List<Ring>> {
        @Override
        protected List<Ring> doInBackground(Void... voids) {
            return new PlaceFetchr().fetchRing("Men");

        }

        @Override
        protected void onPostExecute(List<Ring> list) {
            ringList = list;
            if(mAdapter == null) {
                mAdapter = new RingAdapter(ringList);
                mRecyclerView.setAdapter(mAdapter);
            }else{
                mAdapter.notifyDataSetChanged();
            }
            progDailog.dismiss();
//            for (int i = 0; i < ringList.size(); i++) {
//                mTextView.append("\n");
//                mTextView.append((i + 1) + ". Name: " + ringList.get(i).getName());
//                mTextView.append("\n");
//                mTextView.append("Description: " + ringList.get(i).getDescription());
//                mTextView.append("\n");
//                mTextView.append("IMG URL: " + ringList.get(i).getImgURL());
//                mTextView.append("\n");
//                mTextView.append("URL " + ringList.get(i).getWebURL());
//                mTextView.append("\n");
//                mTextView.append("Price " + ringList.get(i).getPrice());
//                mTextView.append("\n");
//                progDailog.dismiss();
//            }
        }
    }

    private class RingHolder extends RecyclerView.ViewHolder {
        public Ring mRing;
        public TextView mName, mDescription, mPrice;
        public ImageView mImageView;

        //public Button mVideoView,mSaveFavorite,mShowFavorite;
        public RingHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.ring_title);
            mDescription = (TextView) itemView.findViewById(R.id.ring_description);
            mPrice = (TextView) itemView.findViewById(R.id.ring_price);
            mImageView = (ImageView) itemView.findViewById(R.id.ring_image);


        }

        public void bindRing(Ring ring) {

            mRing = ring;
            mName.setText(mRing.getName());
            mDescription.setText(mRing.getDescription());
            mPrice.setText(mRing.getPrice());
            Picasso.with(getActivity())
                    .load(mRing.getImgURL())
                    .placeholder(R.drawable.error)
                    .error(R.drawable.error)
                    .into(mImageView);
//            mVideoView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mSong.getStreamingURL()));
//                    startActivity(browserIntent);
//                }
//            });

//            mSaveFavorite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ParseObject music = new ParseObject("FavoriteMusicList");
//                    music.put("Name",mSong.getName());
//                    music.put("Artist",mSong.getSinger());
//                    music.put("ImageURL",mSong.getImgURL());
//                    music.put("StreamingURL",mSong.getStreamingURL());
//                    music.saveInBackground();
//                    Toast.makeText(getActivity(), "Add to list successfully", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            mShowFavorite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(getActivity(),SavedMusicListActivity.class);
//                    startActivity(i);
//                }
//            });
        }

    }
    private class RingAdapter extends RecyclerView.Adapter<RingHolder>{
        private List<Ring> mRings;

        public RingAdapter(List<Ring> rings){
            mRings = rings;
        }

        @Override
        public RingHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.ring_list_item,viewGroup,false);
            return new RingHolder(view);
        }

        @Override
        public void onBindViewHolder(RingHolder holder, int i) {
            Ring mRing = mRings.get(i);
            holder.bindRing(mRing);
        }

        @Override
        public int getItemCount() {
            return mRings.size();
        }

    }
}
