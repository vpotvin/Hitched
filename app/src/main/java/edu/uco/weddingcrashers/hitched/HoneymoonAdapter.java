package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KALIDY12 on 2/18/2016.
 */
public class HoneymoonAdapter extends ArrayAdapter<HoneymoonItem> {
    public HoneymoonAdapter(Context context, ArrayList<HoneymoonItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoneymoonItem honeymoonItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.honeymoon_item, parent, false);
        }

        ImageView Image = (ImageView) convertView.findViewById(R.id.honeymoonimg);
        TextView Name = (TextView) convertView.findViewById(R.id.honeymoonname);

        Image.setImageResource(honeymoonItem.getImg());
        Image.setScaleType(ImageView.ScaleType.CENTER);

        Name.setText(honeymoonItem.getName());


        return convertView;
    }
}
