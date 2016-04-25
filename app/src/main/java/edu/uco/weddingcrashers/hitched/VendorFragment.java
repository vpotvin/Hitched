package edu.uco.weddingcrashers.hitched;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
/*import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.squareup.picasso.Picasso;*/

import java.util.List;

/**
 * Created by PC User on 2/4/2016.
 */
public class VendorFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private static final String API_KEY="AIzaSyCIfbPXJZ4l_JQof-zQvJNwITN6TmUGNMk";
    private static final String ARG_PLACE_ID = "placeID";
    private static final String ARG_ICON_URL = "iconURL";
    private static final String TAG = "PlaceAPI";
    private GoogleApiClient mGoogleApiClient;
   /* private Place mPlace;*/
    private String placeID, iconURL;
    TextView mName, mAddress, mPhone, mWeb, mReview;
    ImageView mImageView;
    RatingBar mRatingBar;
    List<Review> mReviewList;
    private ProgressDialog progDailog;

    public static VendorFragment newInstance(String placeID, String iconURL) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLACE_ID, placeID);
        args.putSerializable(ARG_ICON_URL, iconURL);

        VendorFragment fragment = new VendorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchReviewTask().execute();
        setHasOptionsMenu(true);
        placeID = (String) getArguments().getSerializable(ARG_PLACE_ID);
        iconURL = (String) getArguments().getSerializable(ARG_ICON_URL);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor, container, false);
        mName = (TextView) view.findViewById(R.id.place_text_view_name);
        mAddress = (TextView) view.findViewById(R.id.place_text_view_address);
        mPhone = (TextView) view.findViewById(R.id.place_text_view_phone);
        mReview = (TextView) view.findViewById(R.id.place_text_view_comment);
        mWeb = (TextView) view.findViewById(R.id.place_text_view_website);
        mImageView = (ImageView) view.findViewById(R.id.place_web_view_image);
        mRatingBar = (RatingBar) view.findViewById(R.id.place_rating);
        progDailog = ProgressDialog.show(getActivity(), "Loading","Please wait...", true);
        progDailog.setCancelable(false);
/*
        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();*/
        getPlace();
        return view;

    }

    public void getPlace() {
        mGoogleApiClient.connect();

        /*Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeID)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            mPlace = places.get(0);

                            mName.setText(mPlace.getName());
                            mAddress.setText(mPlace.getAddress());
                            mPhone.setText(mPlace.getPhoneNumber());
                            if(mPlace.getWebsiteUri() != null)
                                mWeb.setText(mPlace.getWebsiteUri().toString());
                            mRatingBar.setRating(mPlace.getRating());
                            String url = Uri.parse("https://maps.googleapis.com/maps/api/place/photo")
                                    .buildUpon()
                                    .appendQueryParameter("maxwidth","600")
                                    .appendQueryParameter("photoreference", iconURL)
                                    .appendQueryParameter("key", API_KEY).build().toString();
                            Picasso.with(getActivity())
                                    .load(url)
                                    .centerCrop()
                                    .resize(600,400)
                                    .into(mImageView);
                            Log.i(TAG, "Place found: " + mPlace.getName());
                            Log.i(TAG,"URL request: " + url);
                        } else {
                            Log.e(TAG, "Error");
                        }
                        places.release();
                    }
                });
*/
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
        Log.e(TAG, "error when connect");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_vendors_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_save_vendor:
                SavedVendor savedVendor = new SavedVendor();
                savedVendor.setId(placeID);
                savedVendor.setName(mName.getText().toString());
                savedVendor.setAddress(mAddress.getText().toString());
                savedVendor.setPhone(mPhone.getText().toString());
                savedVendor.setRating(String.valueOf(mRatingBar.getRating()));
                savedVendor.setWebsite(mWeb.getText().toString());
                if(iconURL == null)
                    iconURL="Unknown";
                savedVendor.setImgURL(iconURL);
                boolean flag = SavedVendorList.get(getActivity()).addSavedVendor(savedVendor);
                if(flag == true)
                    Toast.makeText(getActivity(),"Saved Successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"This vendor is already on the list",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_favorite:
                Intent i = new Intent(getActivity(),SavedVendorListActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_item_web:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mWeb.getText().toString()));
                startActivity(browserIntent);
                return true;
            case R.id.menu_item_call:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+mPhone.getText().toString()));
                startActivity(intent);
                return true;
            case R.id.menu_item_review:
                Intent reviewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mReviewList.get(0).getUrl()));
                reviewIntent.setPackage("com.google.android.apps.maps");
                if (reviewIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(reviewIntent);
                }

//                FragmentManager manager = getActivity().getSupportFragmentManager();
//                SaveVendorFragment dialog = SaveVendorFragment.newInstance(mReviewList.get(0).getUrl());
//                dialog.show(manager,"DIALOG_RATE");

            default:return super.onOptionsItemSelected(item);
        }

    }

    private class FetchReviewTask extends AsyncTask<Void,Void,List<Review>> {
        @Override
        protected List<Review> doInBackground(Void... voids) {
            return new PlaceFetchr().fetchVendorReview(placeID);

        }

        @Override
        protected void onPostExecute(List<Review> reviewList) {
            mReviewList = reviewList;
            for(int i = 0;i<reviewList.size();i++){
                mReview.append("\n");
                mReview.append((i+1)+". Name: " +reviewList.get(i).getAuthorName());
                mReview.append("\n");
                mReview.append("Rating: "+reviewList.get(i).getRating());
                mReview.append("\n");
                mReview.append("Time: "+reviewList.get(i).getTime());
                mReview.append("\n");
                mReview.append(reviewList.get(i).getText());
                mReview.append("\n");
            }
            progDailog.dismiss();
        }
    }

}
