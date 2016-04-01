package edu.uco.weddingcrashers.hitched;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tung on 3/31/2016.
 */
public class RingFragment extends android.support.v4.app.Fragment {
    private List<Ring> ringList;
    private TextView mTextView;
    private ProgressDialog progDailog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ring, null);
        mTextView = (TextView) view.findViewById(R.id.ring_text_view);
        ringList = new ArrayList<>();
        progDailog = ProgressDialog.show(getActivity(), "Loading", "Please wait...", true);
        progDailog.setCancelable(false);
        new FetchRingTask().execute();
        return view;
    }

    private class FetchRingTask extends AsyncTask<Void, Void, List<Ring>> {
        @Override
        protected List<Ring> doInBackground(Void... voids) {
            return new PlaceFetchr().fetchRing("Rings");

        }

        @Override
        protected void onPostExecute(List<Ring> list) {
            ringList = list;
            for (int i = 0; i < ringList.size(); i++) {
                mTextView.append("\n");
                mTextView.append((i + 1) + ". Name: " + ringList.get(i).getName());
                mTextView.append("\n");
                mTextView.append("Description: " + ringList.get(i).getDescription());
                mTextView.append("\n");
                mTextView.append("IMG URL: " + ringList.get(i).getImgURL());
                mTextView.append("\n");
                mTextView.append("URL " + ringList.get(i).getWebURL());
                mTextView.append("\n");
                mTextView.append("Price " + ringList.get(i).getPrice());
                mTextView.append("\n");
                progDailog.dismiss();
            }
        }
    }
}
