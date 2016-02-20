package edu.uco.weddingcrashers.hitched;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

/**
 * Created by PC User on 2/4/2016.
 */
public class VendorFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG="PlaceAPI";
    private GoogleApiClient mGoogleApiClient;
    private Place mPlace;
    TextView mTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor,container,false);
        mTextView = (TextView) view.findViewById(R.id.place_text_view);
        getPlace();
        return view;

    }
    public void getPlace(){

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        Places.GeoDataApi.getPlaceById(mGoogleApiClient,"ChIJl0i7rChosocRBl0nZ3IcVyw")
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if(places.getStatus().isSuccess()&&places.getCount()>0) {
                            mPlace = places.get(0);
                            mTextView.setText(mPlace.getName());
                            Toast.makeText(getActivity(),mPlace.getName(),Toast.LENGTH_LONG).show();
                            Log.i(TAG,"Place found: " + mPlace.getName());
                        }
                        else {
                            Log.e(TAG,"Error");
                        }
                        places.release();
                    }
                });

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
        Log.e(TAG,"error when connect");
    }
}
