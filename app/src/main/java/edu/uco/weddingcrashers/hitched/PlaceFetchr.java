package edu.uco.weddingcrashers.hitched;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by PC User on 2/19/2016.
 */
public class PlaceFetchr {

    private static final String TAG = "PlaceFetchr";
    private static final String API_KEY = "AIzaSyCIfbPXJZ4l_JQof-zQvJNwITN6TmUGNMk";

    public byte[] getURLBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ":with" + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    String getURLString(String urlSpec) throws IOException {
        return new String(getURLBytes(urlSpec));
    }

    public List<Review> fetchVendorReview(String placeid){
        List<Review> reviewList = new ArrayList<>();
        try{
            String url = Uri.parse("https://maps.googleapis.com/maps/api/place/details/json")
                    .buildUpon()
                    .appendQueryParameter("placeid",placeid)
                    .appendQueryParameter("key",API_KEY).build().toString();
            String jsonString = getURLString(url);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseReview(reviewList,jsonBody);
        }catch (JSONException je){
            Log.e("VendorReview","Failed to parseJSON",je);
        }catch (IOException ioe){
            Log.e("VendorReview","Failed to fetch rating",ioe);
        }
        return reviewList;
    }

    public List<VendorPlace> fetchItems(String querySearch) {
        List<VendorPlace> items = new ArrayList<>();

        try {
            String url = Uri.parse("https://maps.googleapis.com/maps/api/place/textsearch/json")
                    .buildUpon()
                    .appendQueryParameter("query", querySearch)
                    .appendQueryParameter("key", API_KEY).build().toString();
            String jsonString = getURLString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return items;
    }

    private void parseReview(List<Review> items,JSONObject jsonBody) throws IOException,JSONException{
        JSONObject resultJsonObject = jsonBody.getJSONObject("result");
        JSONArray reviewJsonArray = resultJsonObject.getJSONArray("reviews");
        for(int i = 0;i<reviewJsonArray.length();i++){
            Review mReview = new Review();
            Vector<String> reviews = new Vector<>();
            JSONObject reviewJsonObject = reviewJsonArray.getJSONObject(i);
            Log.i("TAG","review found:" + reviewJsonObject.getString("text"));
            mReview.setAuthorName(reviewJsonObject.getString("author_name"));
            mReview.setRating(resultJsonObject.getString("rating"));
            mReview.setText(resultJsonObject.getString("text"));
            mReview.setTime(resultJsonObject.getString("time"));
            items.add(mReview);
//            reviews.add(reviewJasonObject.getString("author_name"));
//            reviews.add(reviewJasonObject.getString("rating"));
//            reviews.add(reviewJasonObject.getString("text"));
        }
    }
    private void parseItems(List<VendorPlace> items, JSONObject jsonBody) throws IOException, JSONException {
        JSONArray resultJsonArray = jsonBody.getJSONArray("results");

        for (int i = 0; i < resultJsonArray.length(); i++) {
            VendorPlace item = new VendorPlace();
            JSONObject resultJsonObject = resultJsonArray.getJSONObject(i);
            if (resultJsonObject.has("photos")) {
                JSONArray photoJsonArray = resultJsonObject.getJSONArray("photos");
                if (photoJsonArray.length() > 0) {
                    for (int j = 0; j < photoJsonArray.length(); j++) {
                        JSONObject photoJsonObject = photoJsonArray.getJSONObject(j);
                        item.setIconURL(photoJsonObject.getString("photo_reference"));
                    }
                }
            }
            item.setAddress(resultJsonObject.getString("formatted_address"));
            item.setName(resultJsonObject.getString("name"));
            item.setID(resultJsonObject.getString("place_id"));

            // item.setPriceLevel(resultJsonObject.getString("price_level"));
            //item.setRating(resultJsonObject.getString("rating"));
            items.add(item);
        }

    }
}
