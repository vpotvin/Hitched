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

/**
 * Created by PC User on 2/19/2016.
 */
public class PhotographerPlaceFetchr {

    private static final String TAG = "PhotographerPlaceFetchr";
    private static final String API_KEY = "AIzaSyB4VW8WLVoirmI7UWPCCAmgopVYWG_Unns";

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

    public List<PhotographerReview> fetchVendorReview(String placeid){
        List<PhotographerReview> photographerReviewList = new ArrayList<>();
        try{
            String url = Uri.parse("https://maps.googleapis.com/maps/api/place/details/json")
                    .buildUpon()
                    .appendQueryParameter("placeid",placeid)
                    .appendQueryParameter("key",API_KEY).build().toString();
            String jsonString = getURLString(url);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseReview(photographerReviewList,jsonBody);
        }catch (JSONException je){
            Log.e("VendorReview","Failed to parseJSON",je);
        }catch (IOException ioe){
            Log.e("VendorReview","Failed to fetch rating",ioe);
        }
        return photographerReviewList;
    }

    public List<PhotographerPlace> fetchItems(String querySearch) {
        List<PhotographerPlace> items = new ArrayList<>();

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

    private void parseReview(List<PhotographerReview> items,JSONObject jsonBody) throws IOException,JSONException{
        JSONObject resultJsonObject = jsonBody.getJSONObject("result");
        JSONArray reviewJsonArray = resultJsonObject.getJSONArray("reviews");
        for(int i = 0;i<reviewJsonArray.length();i++){
            PhotographerReview mPhotographerReview = new PhotographerReview();
            JSONObject reviewJsonObject = reviewJsonArray.getJSONObject(i);
            Log.i("TAG","review found:" + reviewJsonObject.getString("text"));
            mPhotographerReview.setAuthorName(reviewJsonObject.getString("author_name"));
            mPhotographerReview.setRating(reviewJsonObject.getString("rating"));
            mPhotographerReview.setText(reviewJsonObject.getString("text"));
            mPhotographerReview.setTime(reviewJsonObject.getString("time"));
            mPhotographerReview.setUrl(resultJsonObject.getString("url"));
            items.add(mPhotographerReview);
//            reviews.add(reviewJasonObject.getString("author_name"));
//            reviews.add(reviewJasonObject.getString("rating"));
//            reviews.add(reviewJasonObject.getString("text"));
        }
    }
    private void parseItems(List<PhotographerPlace> items, JSONObject jsonBody) throws IOException, JSONException {
        JSONArray resultJsonArray = jsonBody.getJSONArray("results");

        for (int i = 0; i < resultJsonArray.length(); i++) {
            PhotographerPlace item = new PhotographerPlace();
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
