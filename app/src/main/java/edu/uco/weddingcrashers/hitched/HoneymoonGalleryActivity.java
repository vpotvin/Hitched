package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by zhouy_000 on 2016/2/19.
 */
public class HoneymoonGalleryActivity extends Activity {

    private Integer[] pics={R.drawable.red_beach,R.drawable.caldera_at_sunset,R.drawable.boat_at_kamari_beach};

    private ImageView imageview;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Context context=getApplicationContext();
        setContentView(R.layout.honeymoon_gallery);
        Gallery gallery=(Gallery) findViewById(R.id.honeymoongallery);


        gallery.setAdapter(new HoneymoonGalleryAdapter(context));
        imageview=(ImageView) findViewById(R.id.honeymoongalleryimage);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Picture:"+position,Toast.LENGTH_SHORT).show();
                imageview.setImageResource(pics[position]);
            }
        });


    }




    public class HoneymoonGalleryAdapter extends BaseAdapter{
        private Context context;
        int imageBackground;
        public  HoneymoonGalleryAdapter(Context context){
            this.context= context;
        }

        @Override
        public int getCount() {
            return pics.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView image=new ImageView(context);
            image.setImageResource(pics[position]);
            return image;
        }
    }

}
