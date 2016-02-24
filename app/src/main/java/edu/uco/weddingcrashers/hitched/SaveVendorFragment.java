package edu.uco.weddingcrashers.hitched;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

;

/**
 * Created by PC User on 2/18/2016.
 */
public class SaveVendorFragment extends DialogFragment {
    private EditText name,website,phone,email,address,review;
    private RatingBar mRatingBar;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_save_vendor,null);
        name = (EditText)view.findViewById(R.id.edit_text_vendor_name);
        website = (EditText)view.findViewById(R.id.edit_text_vendor_website);
        phone = (EditText)view.findViewById(R.id.edit_text_vendor_phone);
        email = (EditText)view.findViewById(R.id.edit_text_vendor_email);
        address = (EditText)view.findViewById(R.id.edit_text_vendor_address);
        review = (EditText)view.findViewById(R.id.edit_text_vendor_review);
        mRatingBar=(RatingBar)view.findViewById(R.id.vendor_rating_bar);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Save Vendor")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SavedVendor savedVendor = new SavedVendor();
                        savedVendor.setName(name.getText().toString());
                        savedVendor.setAddress(address.getText().toString());
                        savedVendor.setPhone(phone.getText().toString());
                        savedVendor.setComment(review.getText().toString());
                        savedVendor.setRating(String.valueOf(mRatingBar.getRating()));
                        SavedVendorList.get(getActivity()).addSavedVendor(savedVendor);
                        Toast.makeText(getActivity(),"Saved Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Discard",null)
                .create();
    }
}
